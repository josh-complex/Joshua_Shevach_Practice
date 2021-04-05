package com.company.JoshuaShevachU1Capstone.service;

import com.company.JoshuaShevachU1Capstone.dao.*;
import com.company.JoshuaShevachU1Capstone.model.*;
import com.company.JoshuaShevachU1Capstone.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GameStoreServiceTest {

    ConsoleDao consoleDao;
    GameDao gameDao;
    TshirtDao tshirtDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;

    GameStoreService service;

    @Before
    public void setUp() throws Exception {
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpTshirtDaoMock();
        setUpProcessingFeeDaoMock();
        setUpSalesTaxRateDaoMock();
        setUpInvoiceDaoMock();

        service = new GameStoreService(consoleDao, gameDao, tshirtDao, invoiceDao, salesTaxRateDao, processingFeeDao);
    }

    private void setUpConsoleDaoMock() {

        consoleDao = mock(ConsoleDaoJdbcImpl.class);

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4 GB");
        console.setProcessor("Nvidia Tegra X1");
        console.setItemType("Consoles");
        console.setPrice(new BigDecimal("299.00"));
        console.setRemainingInventory(5);

        Console testOutput = new Console();
        testOutput.setId(12);
        testOutput.setModel("Switch");
        testOutput.setManufacturer("Nintendo");
        testOutput.setMemoryAmount("4 GB");
        testOutput.setProcessor("Nvidia Tegra X1");
        testOutput.setItemType("Consoles");
        testOutput.setPrice(new BigDecimal("299.00"));
        testOutput.setRemainingInventory(5);

        List<Console> testListOutput = new ArrayList<Console>() {{
            add(testOutput);
        }};

        doReturn(testOutput).when(consoleDao).addConsole(console);
        doReturn(testOutput).when(consoleDao).getConsole(12);
        doReturn(testListOutput).when(consoleDao).getAllConsoles();
        doReturn(testListOutput).when(consoleDao).getConsolesByManufacturer("Nintendo");

    }
    
    private void setUpGameDaoMock() {

        gameDao = mock(GameDaoJdbcImpl.class);

        Game game = new Game();
        game.setTitle("Kingdom Hearts");
        game.setEsrbRating("E for everyone");
        game.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        game.setStudio("Square Enix");
        game.setItemType("Games");
        game.setRemainingInventory(5);
        game.setPrice(new BigDecimal("59.99"));

        Game testOutput = new Game();
        testOutput.setId(42);
        testOutput.setTitle("Kingdom Hearts");
        testOutput.setEsrbRating("E for everyone");
        testOutput.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        testOutput.setStudio("Square Enix");
        testOutput.setItemType("Games");
        testOutput.setRemainingInventory(5);
        testOutput.setPrice(new BigDecimal("59.99"));

        List<Game> testListOutput = new ArrayList<Game>() {{
            add(testOutput);
        }};

        doReturn(testOutput).when(gameDao).addGame(game);
        doReturn(testOutput).when(gameDao).getGame(42);
        doReturn(testListOutput).when(gameDao).getAllGames();
        doReturn(testListOutput).when(gameDao).getGamesByTitle("Kingdom Hearts");
        doReturn(testListOutput).when(gameDao).getGamesByEsrbRating("E for everyone");
        doReturn(testListOutput).when(gameDao).getGamesByStudio("Square Enix");
        
    }

    private void setUpTshirtDaoMock() {

        tshirtDao = mock(TshirtDaoJdbcImpl.class);

        Tshirt tshirt = new Tshirt();
        tshirt.setSize("Medium");
        tshirt.setColor("White");
        tshirt.setDescription("Cool comfort fabrics");
        tshirt.setItemType("T-Shirts");
        tshirt.setPrice(new BigDecimal("52.95"));
        tshirt.setRemainingInventory(32);

        Tshirt testOutput = new Tshirt();
        testOutput.setId(12);
        testOutput.setSize("Medium");
        testOutput.setColor("White");
        testOutput.setDescription("Cool comfort fabrics");
        testOutput.setItemType("T-Shirts");
        testOutput.setPrice(new BigDecimal("52.95"));
        testOutput.setRemainingInventory(32);

        List<Tshirt> testListOutput = new ArrayList<Tshirt>() {{
            add(testOutput);
        }};

        doReturn(testOutput).when(tshirtDao).addTshirt(tshirt);
        doReturn(testOutput).when(tshirtDao).getTshirt(12);
        doReturn(testListOutput).when(tshirtDao).getAllTshirts();
        doReturn(testListOutput).when(tshirtDao).getTshirtsBySize("Medium");
        doReturn(testListOutput).when(tshirtDao).getTshirtsByColor("White");

    }

    private void setUpInvoiceDaoMock() {

        invoiceDao = mock(InvoiceDaoJdbcImpl.class);

        Invoice invoice = new Invoice();
        invoice.setName("Joshua Shevach");
        invoice.setCity("Orlando");
        invoice.setState("FL");
        invoice.setStreet("1110 Bassano Way");
        invoice.setZipcode("32828");
        invoice.setItemType("T-Shirts");
        invoice.setItemId(12);
        invoice.setUnitPrice(new BigDecimal("52.95"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("105.90"));
        invoice.setTax(new BigDecimal("6.35"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("114.23"));

        Invoice testOutput = new Invoice();
        testOutput.setId(42);
        testOutput.setName("Joshua Shevach");
        testOutput.setCity("Orlando");
        testOutput.setState("FL");
        testOutput.setStreet("1110 Bassano Way");
        testOutput.setZipcode("32828");
        testOutput.setItemType("T-Shirts");
        testOutput.setItemId(12);
        testOutput.setUnitPrice(new BigDecimal("52.95"));
        testOutput.setQuantity(2);
        testOutput.setSubtotal(new BigDecimal("105.90"));
        testOutput.setTax(new BigDecimal("6.35"));
        testOutput.setProcessingFee(new BigDecimal("1.98"));
        testOutput.setTotal(new BigDecimal("114.23"));

        List<Invoice> testListOutput = new ArrayList<Invoice>() {{
           add(testOutput);
        }};

        doReturn(testOutput).when(invoiceDao).addInvoice(invoice);
        doReturn(testOutput).when(invoiceDao).getInvoice(42);
        doReturn(testListOutput).when(invoiceDao).getAllInvoices();
        doNothing().when(invoiceDao).deleteInvoice(42);

    }

    private void setUpProcessingFeeDaoMock() {

        processingFeeDao = mock(ProcessingFeeDaoJdbcImpl.class);

        doReturn(new ProcessingFee("T-Shirts", new BigDecimal("1.98")))
                .when(processingFeeDao).getProcessingFeeByProductType("T-Shirts");
        doReturn(new ProcessingFee("Consoles", new BigDecimal("14.99")))
                .when(processingFeeDao).getProcessingFeeByProductType("Consoles");
        doReturn(new ProcessingFee("Games", new BigDecimal("1.49")))
                .when(processingFeeDao).getProcessingFeeByProductType("Games");

    }

    private void setUpSalesTaxRateDaoMock() {

        salesTaxRateDao = mock(SalesTaxRateDaoJdbcImpl.class);

        doReturn(new SalesTaxRate("FL", new BigDecimal(".06")))
                .when(salesTaxRateDao).getSalesTaxRateByState("FL");

    }

    @Test
    public void shouldAddAndFindInvoice() {

        Invoice invoice = new Invoice();
        invoice.setName("Joshua Shevach");
        invoice.setCity("Orlando");
        invoice.setState("FL");
        invoice.setStreet("1110 Bassano Way");
        invoice.setZipcode("32828");
        invoice.setItemId(12);
        invoice.setItemType("T-Shirts");
        invoice.setQuantity(2);

        InvoiceViewModel viewModel = service.addInvoice(invoice);
        InvoiceViewModel viewModelFromService = service.getInvoice(viewModel.getId());
        List<InvoiceViewModel> expectedViewModelList = service.getAllInvoices();

        verify(processingFeeDao, times(3)).getProcessingFeeByProductType(invoice.getItemType());
        verify(salesTaxRateDao, times(3)).getSalesTaxRateByState(invoice.getState());

        assertEquals(viewModel, viewModelFromService);
        assertEquals(1, expectedViewModelList.size());
    }

    @Test
    public void shouldAddAndFindConsoleByIdAndManufacturer() {

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4 GB");
        console.setProcessor("Nvidia Tegra X1");
        console.setItemType("Consoles");
        console.setPrice(new BigDecimal("299.00"));
        console.setRemainingInventory(5);

        console = service.addConsole(console);
        Console consoleFromService = service.getConsoleById(console.getId());
        List<Console> expectedConsoles = service.getAllConsoles();
        List<Console> expectedConsolesFromManufacturer = service.getConsolesByManufacturer("Nintendo");

        assertEquals(console, consoleFromService);
        assertEquals(1, expectedConsoles.size());
        assertEquals(1, expectedConsolesFromManufacturer.size());

    }

    @Test
    public void shouldAddAndFindGameByIdTitleRatingAndStudio() {

        Game game = new Game();
        game.setTitle("Kingdom Hearts");
        game.setEsrbRating("E for everyone");
        game.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        game.setStudio("Square Enix");
        game.setItemType("Games");
        game.setRemainingInventory(5);
        game.setPrice(new BigDecimal("59.99"));

        game = service.addGame(game);
        Game gameFromService = service.getGameById(game.getId());
        List<Game> expectedGames = service.getAllGames();
        List<Game> expectedGamesFromTitle = service.getGamesByTitle(game.getTitle());
        List<Game> expectedGamesFromRating = service.getGamesByEsrbRating(game.getEsrbRating());
        List<Game> expectedGamesFromStudio = service.getGamesByStudio(game.getStudio());

        assertEquals(game, gameFromService);
        assertEquals(1, expectedGames.size());
        assertEquals(1, expectedGamesFromTitle.size());
        assertEquals(1, expectedGamesFromRating.size());
        assertEquals(1, expectedGamesFromStudio.size());

    }

    @Test
    public void shouldAddAndFindTshirtByIdSizeAndColor() {

        Tshirt tshirt = new Tshirt();
        tshirt.setSize("Medium");
        tshirt.setColor("White");
        tshirt.setDescription("Cool comfort fabrics");
        tshirt.setItemType("T-Shirts");
        tshirt.setPrice(new BigDecimal("52.95"));
        tshirt.setRemainingInventory(32);

        tshirt = service.addTshirt(tshirt);
        Tshirt tshirtFromService = service.getTshirtById(tshirt.getId());
        List<Tshirt> expectedTshirts = service.getAllTshirts();
        List<Tshirt> expectedTshirtsFromSize = service.getTshirtsBySize(tshirt.getSize());
        List<Tshirt> expectedTshirtsFromColor = service.getTshirtsByColor(tshirt.getColor());

        assertEquals(tshirt, tshirtFromService);
        assertEquals(1, expectedTshirts.size());
        assertEquals(1, expectedTshirtsFromSize.size());
        assertEquals(1, expectedTshirtsFromColor.size());

    }

}
