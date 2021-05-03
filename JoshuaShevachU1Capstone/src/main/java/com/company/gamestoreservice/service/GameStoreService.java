package com.company.gamestoreservice.service;

import com.company.gamestoreservice.dao.*;
import com.company.gamestoreservice.exception.LowInventoryException;
import com.company.gamestoreservice.exception.NotFoundException;
import com.company.gamestoreservice.model.*;
import com.company.gamestoreservice.viewmodel.InvoiceViewModel;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameStoreService {

    ConsoleRepo consoleRepo;
    GameRepo gameRepo;
    TshirtRepo tShirtRepo;
    InvoiceRepo invoiceRepo;
    SalesTaxRateRepo salesTaxRateRepo;
    ProcessingFeeRepo processingFeeRepo;

    @Autowired
    public GameStoreService(ConsoleRepo consoleRepo, GameRepo gameRepo, TshirtRepo tShirtRepo, InvoiceRepo invoiceRepo, SalesTaxRateRepo salesTaxRateRepo, ProcessingFeeRepo processingFeeRepo) {
        this.consoleRepo = consoleRepo;
        this.gameRepo = gameRepo;
        this.tShirtRepo = tShirtRepo;
        this.invoiceRepo = invoiceRepo;
        this.salesTaxRateRepo = salesTaxRateRepo;
        this.processingFeeRepo = processingFeeRepo;
    }

    public Console saveConsole(Console console) {
        if (console.getConsoleId() != null) consoleRepo.getOne(console.getConsoleId());
        return consoleRepo.save(console);
    }

    public Console getConsoleById(Integer id) {
        var console = consoleRepo.findById(id);
        if (console.isPresent()) return console.get();
        throw new EntityNotFoundException("Could not find console with id: " + id);
    }

    public List<Console> getAllConsoles() {
        return consoleRepo.findAll();
    }

    public List<Console> getConsolesByManufacturer(String manufacturer) {
        return consoleRepo.findAllByManufacturer(manufacturer);
    }

    public void deleteConsole(Integer id) {
        consoleRepo.deleteById(id);
    }

    public Tshirt getTshirtById(Integer id) {
        var tshirt = tShirtRepo.findById(id);
        if (tshirt.isPresent()) return tshirt.get();
        throw new EntityNotFoundException("Could not find t-shirt with id: " + id);
    }

    public List<Tshirt> getAllTshirts() {
        return tShirtRepo.findAll();
    }

    public Tshirt saveTshirt(Tshirt tshirt) {
        if (tshirt.getTShirtId() != null) tShirtRepo.getOne(tshirt.getTShirtId());
        return tShirtRepo.save(tshirt);
    }

    public List<Tshirt> getTshirtsByColor(String color) {
        return tShirtRepo.findAllByColor(color);
    }

    public List<Tshirt> getTshirtsBySize(String size) {
        return tShirtRepo.findAllBySize(size);
    }

    public void deleteTshirt(Integer id) {
        tShirtRepo.deleteById(id);
    }

    public Game getGameById(Integer id) {
        var game = gameRepo.findById(id);
        if (game.isPresent()) return game.get();
        throw new EntityNotFoundException("Could not find game with id: " + id);
    }

    public Game saveGame(Game game) {
        if (game.getGameId() != null) gameRepo.getOne(game.getGameId());
        return gameRepo.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    public List<Game> getGamesByEsrbRating(String rating) {
        return gameRepo.findAllByEsrbRating(rating);
    }

    public List<Game> getGamesByStudio(String studio) {
        return gameRepo.findAllByStudio(studio);
    }

    public List<Game> getGamesByTitle(String title) {
        return gameRepo.findAllByTitleContains(title);
    }

    public void deleteGame(Integer id) {
        gameRepo.deleteById(id);
    }

    @Transactional
    public InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        var vm = new InvoiceViewModel();
        vm.setInvoiceId(invoice.getInvoiceId());
        vm.setName(invoice.getName());
        vm.setStreet(invoice.getStreet());
        vm.setCity(invoice.getCity());
        vm.setState(invoice.getState());
        vm.setZipcode(invoice.getZipcode());
        vm.setQuantity(invoice.getQuantity());
        vm.setItemType(invoice.getItemType());

        //Sales tax rate information
        var taxRate = salesTaxRateRepo.findByState(vm.getState());
        if (taxRate != null) vm.setSalesTaxRate(taxRate);
        else throw new NotFoundException("Please enter a valid state");

        //Item lookup and inventory modification
        switch (invoice.getItemType().toLowerCase()) {

            case "t-shirts":
                var tShirt = tShirtRepo.getOne(invoice.getItemId());
                vm.setItemId(tShirt.getTShirtId());
                vm.setUnitPrice(tShirt.getPrice());
                if (vm.getInvoiceId() == null) {
                    if (invoice.getQuantity() <= tShirt.getQuantity()) {
                        tShirt.setQuantity(tShirt.getQuantity() - invoice.getQuantity());
                        saveTshirt(tShirt);
                    } else throw new LowInventoryException("Quantity requested is greater than current inventory");
                }
                break;

            case "consoles":
                var console = consoleRepo.getOne(invoice.getItemId());
                vm.setItemId(console.getConsoleId());
                vm.setUnitPrice(console.getPrice());
                if (vm.getInvoiceId() == null) {
                    if (invoice.getQuantity() <= console.getQuantity()) {
                        console.setQuantity(console.getQuantity() - invoice.getQuantity());
                        saveConsole(console);
                    } else throw new LowInventoryException("Quantity requested is greater than current inventory");
                }
                break;

            case "games":
                var game = gameRepo.getOne(invoice.getItemId());
                vm.setItemId(game.getGameId());
                vm.setUnitPrice(game.getPrice());
                if (vm.getInvoiceId() == null) {
                    if (game.getQuantity() >= invoice.getQuantity()) {
                        game.setQuantity(game.getQuantity() - invoice.getQuantity());
                        saveGame(game);
                    } else throw new LowInventoryException("Quantity requested is greater than current inventory");
                }
                break;

            default:
                throw new NotFoundException("Please enter a valid item type");

        }

        vm.setProcessingFee(processingFeeRepo.findByProductType(invoice.getItemType()));

        //Final calculations
        vm.setSubtotal(vm.getUnitPrice().multiply(new BigDecimal(vm.getQuantity())).setScale(2, RoundingMode.HALF_UP));

        var tax = vm.getSubtotal().multiply(vm.getSalesTaxRate().getRate()).setScale(2, RoundingMode.HALF_UP);
        var processingFee = invoice.getQuantity() > 10
                ? vm.getProcessingFee().getFee().add(new BigDecimal("15.49"))
                : vm.getProcessingFee().getFee();

        vm.setTaxesAndFees(tax.add(processingFee).setScale(2, RoundingMode.HALF_UP));
        vm.setTotal(vm.getSubtotal().add(vm.getTaxesAndFees()));

        return vm;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(Invoice invoice) {
        InvoiceViewModel vm = buildInvoiceViewModel(invoice);

        invoice.setUnitPrice(vm.getUnitPrice());
        invoice.setSubtotal(vm.getSubtotal());
        invoice.setTax(vm.getSubtotal().multiply(vm.getSalesTaxRate().getRate()).setScale(2, RoundingMode.HALF_UP));
        invoice.setProcessingFee(
                invoice.getQuantity() > 10
                        ? vm.getProcessingFee().getFee().add(new BigDecimal("15.49"))
                        : vm.getProcessingFee().getFee());
        invoice.setTotal(vm.getTotal());

        invoice = invoiceRepo.save(invoice);
        vm.setInvoiceId(invoice.getInvoiceId());

        return vm;
    }

    @Transactional
    public InvoiceViewModel getInvoice(Integer id) {
        var invoice = invoiceRepo.findById(id);
        if (invoice.isPresent()) return buildInvoiceViewModel(invoice.get());
        throw new EntityNotFoundException("Could not find invoice with id: " + id);
    }

    @Transactional
    public List<InvoiceViewModel> getAllInvoices() {
        return invoiceRepo.findAll().stream()
                .map(this::buildInvoiceViewModel)
                .collect(Collectors.toList());
    }
}
