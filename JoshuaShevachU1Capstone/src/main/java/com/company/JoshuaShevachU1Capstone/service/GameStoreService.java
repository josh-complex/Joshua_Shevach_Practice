package com.company.JoshuaShevachU1Capstone.service;

import com.company.JoshuaShevachU1Capstone.dao.*;
import com.company.JoshuaShevachU1Capstone.exception.LowInventoryException;
import com.company.JoshuaShevachU1Capstone.exception.NotFoundException;
import com.company.JoshuaShevachU1Capstone.model.*;
import com.company.JoshuaShevachU1Capstone.viewmodel.InvoiceViewModel;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameStoreService {

    ConsoleDao consoleDao;
    GameDao gameDao;
    TshirtDao tshirtDao;
    InvoiceDao invoiceDao;
    SalesTaxRateDao salesTaxRateDao;
    ProcessingFeeDao processingFeeDao;

    @Autowired
    public GameStoreService(ConsoleDao consoleDao, GameDao gameDao, TshirtDao tshirtDao, InvoiceDao invoiceDao, SalesTaxRateDao salesTaxRateDao, ProcessingFeeDao processingFeeDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tshirtDao = tshirtDao;
        this.invoiceDao = invoiceDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.processingFeeDao = processingFeeDao;
    }

    public Console addConsole(Console console) {
        return consoleDao.addConsole(console);
    }

    public Console getConsoleById(int id) {
        return consoleDao.getConsole(id);
    }

    public List<Console> getAllConsoles() {
        return consoleDao.getAllConsoles();
    }

    public List<Console> getConsolesByManufacturer(String manufacturer) {
        return consoleDao.getConsolesByManufacturer(manufacturer);
    }

    public void updateConsole(Console console) {
        consoleDao.updateConsole(console);
    }

    public void deleteConsole(int id) {
        consoleDao.deleteConsole(id);
    }

    public Tshirt getTshirtById(int id) {
        return tshirtDao.getTshirt(id);
    }

    public List<Tshirt> getAllTshirts() {
        return tshirtDao.getAllTshirts();
    }

    public Tshirt addTshirt(Tshirt tshirt) {
        return tshirtDao.addTshirt(tshirt);
    }

    public List<Tshirt> getTshirtsByColor(String color) {
        return tshirtDao.getTshirtsByColor(color);
    }

    public List<Tshirt> getTshirtsBySize(String size) {
        return tshirtDao.getTshirtsBySize(size);
    }

    public void updateTshirt(Tshirt tshirt) {
        tshirtDao.updateTshirt(tshirt);
    }

    public void deleteTshirt(int id) {
        tshirtDao.deleteTshirt(id);
    }

    public Game getGameById(int id) {
        return gameDao.getGame(id);
    }

    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    public List<Game> getGamesByEsrbRating(String rating) {
        return gameDao.getGamesByEsrbRating(rating);
    }

    public List<Game> getGamesByStudio(String studio) {
        return gameDao.getGamesByStudio(studio);
    }

    public List<Game> getGamesByTitle(String title) {
        return gameDao.getGamesByTitle(title);
    }

    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

    public void deleteGame(int id) {
        gameDao.deleteGame(id);
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

//region Base Setup
        InvoiceViewModel vm = new InvoiceViewModel();
        vm.setId(invoice.getId());
        vm.setName(invoice.getName());
        vm.setStreet(invoice.getStreet());
        vm.setCity(invoice.getCity());
        vm.setState(invoice.getState());
        vm.setZipcode(invoice.getZipcode());
        vm.setQuantity(invoice.getQuantity());
//endregion

//region Get And Verify Tax Rate
        SalesTaxRate taxRate = salesTaxRateDao.getSalesTaxRateByState(vm.getState());
        if(taxRate == null) throw new NotFoundException("Please enter a valid state");
        vm.setSalesTaxRate(taxRate);
//endregion

//region Item Lookup, Modification, Exception Handling
        switch (invoice.getItemType().toLowerCase()) {

            case "t-shirts":
                Tshirt tshirt = getTshirtById(invoice.getItemId());
                if(tshirt == null) throw new NotFoundException("Please enter a valid t-shirt ID");
                vm.setItem(tshirt);
                if(tshirt.getRemainingInventory() >= invoice.getQuantity()) {
                    tshirt.setRemainingInventory(tshirt.getRemainingInventory() - invoice.getQuantity());
                    updateTshirt(tshirt);
                }
                else throw new LowInventoryException("Quantity requested is greater than current inventory");
                break;
            case "consoles":
                Console console = getConsoleById(invoice.getItemId());
                if(console == null) throw new NotFoundException("Please enter a valid console ID");
                vm.setItem(console);
                if(console.getRemainingInventory() >= invoice.getQuantity()) {
                    console.setRemainingInventory(console.getRemainingInventory() - invoice.getQuantity());
                    updateConsole(console);
                }
                else throw new LowInventoryException("Quantity requested is greater than current inventory");
                break;
            case "games":
                Game game = getGameById(invoice.getItemId());
                if(game == null) throw new NotFoundException("Please enter a valid game ID");
                vm.setItem(game);
                if(game.getRemainingInventory() >= invoice.getQuantity()) {
                    game.setRemainingInventory(game.getRemainingInventory() - invoice.getQuantity());
                    updateGame(game);
                }
                else throw new LowInventoryException("Quantity requested is greater than current inventory");
                break;
            default:
                throw new NotFoundException("Please enter a valid item type");

        }
//endregion

        vm.setProcessingFee(processingFeeDao.getProcessingFeeByProductType(invoice.getItemType()));

//region Total, Taxes, Fees Calculations
        vm.setSubtotal(vm.getItem().getPrice().multiply(new BigDecimal(vm.getQuantity())));
        var tax = vm.getSubtotal().multiply(vm.getSalesTaxRate().getRate());
        var processingFee = invoice.getQuantity() > 10
                ? vm.getProcessingFee().getFee().add(new BigDecimal("15.49"))
                : vm.getProcessingFee().getFee();
        vm.setTaxesAndFees(tax.add(processingFee).setScale(2, RoundingMode.HALF_UP));
        vm.setTotal(vm.getSubtotal().add(vm.getTaxesAndFees()));
//endregion

        return vm;
    }

    public InvoiceViewModel addInvoice(Invoice invoice) {
        InvoiceViewModel vm = buildInvoiceViewModel(invoice);

        invoice.setUnitPrice(vm.getItem().getPrice());
        invoice.setSubtotal(vm.getSubtotal());
        invoice.setTax(vm.getSubtotal().multiply(vm.getSalesTaxRate().getRate()).setScale(2, RoundingMode.HALF_UP));
        invoice.setProcessingFee(
                invoice.getQuantity() > 10
                        ? vm.getProcessingFee().getFee().add(new BigDecimal("15.49"))
                        : vm.getProcessingFee().getFee());
        invoice.setTotal(vm.getTotal());

        invoice = invoiceDao.addInvoice(invoice);
        vm.setId(invoice.getId());

        return vm;
    }

    public InvoiceViewModel getInvoice(int id) {
        return buildInvoiceViewModel(invoiceDao.getInvoice(id));
    }

    public List<InvoiceViewModel> getAllInvoices() {
        List<InvoiceViewModel> vms = new ArrayList<>();
        var invoices = invoiceDao.getAllInvoices();
        invoices.forEach(x ->
                vms.add(buildInvoiceViewModel(x))
        );
        return vms;
    }
}
