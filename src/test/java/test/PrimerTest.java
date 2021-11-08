package test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class PrimerTest {
	
	public static void main(String[] args) {
		
		WebDriver driver = start();
		ExtentReports extent = new ExtentReports();
		ExtentTest test1 = config(extent);
		
		
		WebElement eleclick = driver.findElement(By.id("menu-item-1364"));
		eleclick.click();
		System.out.println("Click en contactos");
		test1.pass("Clickeo en contactos");
		
		generarElemento("your-name", "Pedro Bacigalupo Concilio", driver);
		System.out.println("Nombre escrito");
		test1.pass("Nombre escrito");
		
		generarElemento("your-email", "pedro_bacigalupohotmail.com", driver);
		System.out.println("Mail erroneo escrito");
		test1.pass("Mail introducido erroneamente");
		
		generarElemento("your-subject", "Caramelos", driver);
		System.out.println("Asunto escrito");
		test1.pass("Asunto escrito");
		
		generarElemento("your-message", "Dulce o truco", driver);
		System.out.println("Mensaje escrito");
		test1.pass("Mensaje escrito");
		
		WebElement enviar = driver.findElement(By.cssSelector(".wpcf7-form-control.wpcf7-submit"));
		enviar.click();
		System.out.println("Click en enviar");
		test1.pass("Click en enviar");
		
		if (errorMensaje(".wpcf7-not-valid-tip", driver).equals("La dirección e-mail parece inválida.")) {
			System.out.println("Mensaje de mail erroneo detectado");
			test1.pass("Alerta mail erroneo");
		}
		
		extent.flush();
		driver.close();
		
	}
	
	public static void generarElemento(String nombre, String texto, WebDriver driver) {
		WebElement elementoWeb= driver.findElement(By.name(nombre));
		elementoWeb.sendKeys(texto);
	}
	
	public static WebDriver start() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.consultoriaglobal.com.ar/cgweb/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		return driver;
	}
	
	public static ExtentTest config(ExtentReports extent) {
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter("extentReports.html");
        extent.attachReporter(htmlreporter);
        ExtentTest test1 = extent.createTest("test");
        return test1;
	}
	
	public static String errorMensaje(String path, WebDriver driver) {
		WebElement elegetText = driver.findElement(By.cssSelector(path));
		String error = elegetText.getText();
		return error;	
	}

}
