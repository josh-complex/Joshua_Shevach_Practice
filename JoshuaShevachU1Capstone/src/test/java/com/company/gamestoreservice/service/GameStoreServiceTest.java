package com.company.gamestoreservice.service;

import com.company.gamestoreservice.dao.*;
import com.company.gamestoreservice.model.*;
import com.company.gamestoreservice.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameStoreServiceTest {

    @MockBean
    private ConsoleRepo consoleRepo;
    @MockBean
    private GameRepo gameRepo;
    @MockBean
    private TshirtRepo tshirtRepo;
    @MockBean
    private InvoiceRepo invoiceRepo;
    @MockBean
    private ProcessingFeeRepo processingFeeRepo;
    @MockBean
    private SalesTaxRateRepo salesTaxRateRepo;

    @Autowired
    private GameStoreService service;

    private Console inputConsole;
    private final List<Console> outputConsoles = new ArrayList<>();

    private Game inputGame;
    private final List<Game> outputGames = new ArrayList<>();

    private Tshirt inputTshirt;
    private final List<Tshirt> outputTshirts = new ArrayList<>();

    private Invoice inputInvoice;
    private final List<Invoice> outputInvoices = new ArrayList<>();


    @Before
    public void setUp() {
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpTshirtDaoMock();
        setUpProcessingFeeDaoMock();
        setUpSalesTaxRateDaoMock();
        setUpInvoiceDaoMock();
    }

    private void setUpConsoleDaoMock() {
        inputConsole = new Console();
        inputConsole.setModel("Switch");
        inputConsole.setManufacturer("Nintendo");
        inputConsole.setMemoryAmount("4 GB");
        inputConsole.setProcessor("Nvidia Tegra X1");
        inputConsole.setPrice(new BigDecimal("299.00"));
        inputConsole.setQuantity(5);

        Console outputConsole = new Console();
        outputConsole.setConsoleId(1);
        outputConsole.setModel("Switch");
        outputConsole.setManufacturer("Nintendo");
        outputConsole.setMemoryAmount("4 GB");
        outputConsole.setProcessor("Nvidia Tegra X1");
        outputConsole.setPrice(new BigDecimal("299.00"));
        outputConsole.setQuantity(5);

        outputConsoles.add(outputConsole);

        when(consoleRepo.save(inputConsole)).thenReturn(outputConsole);
        when(consoleRepo.findById(1)).thenReturn(Optional.of(outputConsole));
        when(consoleRepo.getOne(1)).thenReturn(outputConsole);
        when(consoleRepo.findAll()).thenReturn(outputConsoles);
        when(consoleRepo.findAllByManufacturer("Nintendo")).thenReturn(outputConsoles);
    }
    
    private void setUpGameDaoMock() {
        inputGame = new Game();
        inputGame.setTitle("Kingdom Hearts");
        inputGame.setEsrbRating("E for everyone");
        inputGame.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        inputGame.setStudio("Square Enix");
        inputGame.setQuantity(5);
        inputGame.setPrice(new BigDecimal("59.99"));

        Game outputGame = new Game();
        outputGame.setGameId(1);
        outputGame.setTitle("Kingdom Hearts");
        outputGame.setEsrbRating("E for everyone");
        outputGame.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        outputGame.setStudio("Square Enix");
        outputGame.setQuantity(5);
        outputGame.setPrice(new BigDecimal("59.99"));

        outputGames.add(outputGame);

        when(gameRepo.save(inputGame)).thenReturn(outputGame);
        when(gameRepo.findById(1)).thenReturn(Optional.of(outputGame));
        when(gameRepo.getOne(1)).thenReturn(outputGame);
        when(gameRepo.findAll()).thenReturn(outputGames);
        when(gameRepo.findAllByEsrbRating("E for everyone")).thenReturn(outputGames);
        when(gameRepo.findAllByStudio("Square Enix")).thenReturn(outputGames);
        when(gameRepo.findAllByTitleContains("Kingdom Hearts")).thenReturn(outputGames);
    }

    private void setUpTshirtDaoMock() {
        inputTshirt = new Tshirt();
        inputTshirt.setSize("Medium");
        inputTshirt.setColor("White");
        inputTshirt.setDescription("Cool comfort fabrics");
        inputTshirt.setPrice(new BigDecimal("52.95"));
        inputTshirt.setQuantity(32);

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setTShirtId(1);
        outputTshirt.setSize("Medium");
        outputTshirt.setColor("White");
        outputTshirt.setDescription("Cool comfort fabrics");
        outputTshirt.setPrice(new BigDecimal("52.95"));
        outputTshirt.setQuantity(32);

        outputTshirts.add(outputTshirt);


        when(tshirtRepo.save(inputTshirt)).thenReturn(outputTshirt);
        when(tshirtRepo.findById(1)).thenReturn(Optional.of(outputTshirt));
        when(tshirtRepo.getOne(1)).thenReturn(outputTshirt);
        when(tshirtRepo.findAll()).thenReturn(outputTshirts);
        when(tshirtRepo.findAllBySize("Medium")).thenReturn(outputTshirts);
        when(tshirtRepo.findAllByColor("White")).thenReturn(outputTshirts);
    }

    private void setUpInvoiceDaoMock() {
        inputInvoice = new Invoice();
        inputInvoice.setName("Joshua Shevach");
        inputInvoice.setCity("Orlando");
        inputInvoice.setState("FL");
        inputInvoice.setStreet("1110 Bassano Way");
        inputInvoice.setZipcode("32828");
        inputInvoice.setItemType("T-Shirts");
        inputInvoice.setItemId(1);
        inputInvoice.setUnitPrice(new BigDecimal("52.95"));
        inputInvoice.setQuantity(2);
        inputInvoice.setSubtotal(new BigDecimal("105.90"));
        inputInvoice.setTax(new BigDecimal("6.35"));
        inputInvoice.setProcessingFee(new BigDecimal("1.98"));
        inputInvoice.setTotal(new BigDecimal("114.23"));

        Invoice outputInvoice = new Invoice();
        outputInvoice.setInvoiceId(1);
        outputInvoice.setName("Joshua Shevach");
        outputInvoice.setCity("Orlando");
        outputInvoice.setState("FL");
        outputInvoice.setStreet("1110 Bassano Way");
        outputInvoice.setZipcode("32828");
        outputInvoice.setItemType("T-Shirts");
        outputInvoice.setItemId(1);
        outputInvoice.setUnitPrice(new BigDecimal("52.95"));
        outputInvoice.setQuantity(2);
        outputInvoice.setSubtotal(new BigDecimal("105.90"));
        outputInvoice.setTax(new BigDecimal("6.35"));
        outputInvoice.setProcessingFee(new BigDecimal("1.98"));
        outputInvoice.setTotal(new BigDecimal("114.23"));

        outputInvoices.add(outputInvoice);

        when(invoiceRepo.save(inputInvoice)).thenReturn(outputInvoice);
        when(invoiceRepo.findById(1)).thenReturn(Optional.of(outputInvoice));
        when(invoiceRepo.findAll()).thenReturn(outputInvoices);
    }

    private void setUpProcessingFeeDaoMock() {
        doReturn(new ProcessingFee("T-Shirts", new BigDecimal("1.98")))
                .when(processingFeeRepo).findByProductType("T-Shirts");
        doReturn(new ProcessingFee("Consoles", new BigDecimal("14.99")))
                .when(processingFeeRepo).findByProductType("Consoles");
        doReturn(new ProcessingFee("Games", new BigDecimal("1.49")))
                .when(processingFeeRepo).findByProductType("Games");
    }

    private void setUpSalesTaxRateDaoMock() {
        doReturn(new SalesTaxRate("FL", new BigDecimal(".06")))
                .when(salesTaxRateRepo).findByState("FL");
    }

    @Test
    public void shouldAddAndFindInvoice() {

        InvoiceViewModel viewModel = service.saveInvoice(inputInvoice);
        InvoiceViewModel viewModelFromService = service.getInvoice(viewModel.getInvoiceId());
        List<InvoiceViewModel> expectedViewModelList = service.getAllInvoices();

        verify(processingFeeRepo, times(3)).findByProductType(viewModel.getItemType());
        verify(salesTaxRateRepo, times(3)).findByState(viewModel.getState());
        verify(tshirtRepo, times(4)).getOne(viewModel.getItemId());

        assertEquals(viewModel, viewModelFromService);
        assertEquals(1, expectedViewModelList.size());
    }

    @Test
    public void shouldAddAndFindConsoleByIdAndManufacturer() {

        Console console = service.saveConsole(inputConsole);
        Console consoleFromService = service.getConsoleById(console.getConsoleId());
        List<Console> expectedConsoles = service.getAllConsoles();
        List<Console> expectedConsolesFromManufacturer = service.getConsolesByManufacturer("Nintendo");

        assertEquals(console, consoleFromService);
        assertEquals(1, expectedConsoles.size());
        assertEquals(1, expectedConsolesFromManufacturer.size());

        service.deleteConsole(console.getConsoleId());
        verify(consoleRepo, times(1)).deleteById(console.getConsoleId());

    }

    @Test
    public void shouldAddAndFindGameByIdTitleRatingAndStudio() {

        Game game = service.saveGame(inputGame);
        Game gameFromService = service.getGameById(game.getGameId());
        List<Game> expectedGames = service.getAllGames();
        List<Game> expectedGamesFromTitle = service.getGamesByTitle(game.getTitle());
        List<Game> expectedGamesFromRating = service.getGamesByEsrbRating(game.getEsrbRating());
        List<Game> expectedGamesFromStudio = service.getGamesByStudio(game.getStudio());

        assertEquals(game, gameFromService);
        assertEquals(1, expectedGames.size());
        assertEquals(1, expectedGamesFromTitle.size());
        assertEquals(1, expectedGamesFromRating.size());
        assertEquals(1, expectedGamesFromStudio.size());

        service.deleteGame(game.getGameId());
        verify(gameRepo, times(1)).deleteById(game.getGameId());

    }

    @Test
    public void shouldAddAndFindTshirtByIdSizeAndColor() {

        Tshirt tshirt = service.saveTshirt(inputTshirt);
        Tshirt tshirtFromService = service.getTshirtById(tshirt.getTShirtId());
        List<Tshirt> expectedTshirts = service.getAllTshirts();
        List<Tshirt> expectedTshirtsFromSize = service.getTshirtsBySize(tshirt.getSize());
        List<Tshirt> expectedTshirtsFromColor = service.getTshirtsByColor(tshirt.getColor());

        assertEquals(tshirt, tshirtFromService);
        assertEquals(1, expectedTshirts.size());
        assertEquals(1, expectedTshirtsFromSize.size());
        assertEquals(1, expectedTshirtsFromColor.size());

        service.deleteTshirt(tshirt.getTShirtId());
        verify(tshirtRepo, times(1)).deleteById(tshirt.getTShirtId());

    }

}
