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
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.consultoriaglobal.com.ar/cgweb/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter("extentReports.html");
		ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlreporter);
        ExtentTest test1 = extent.createTest("test");
		
		
		WebElement eleclick = driver.findElement(By.id("menu-item-1364"));
		eleclick.click();
		System.out.println("Click en contactos");
		test1.pass("Clickeo en contactos: correcto");
		
		WebElement nombre= driver.findElement(By.name("your-name"));
		nombre.sendKeys("Pedro Bacigalupo Concilio");
		System.out.println("Nombre escrito");
		test1.pass("Nombre escrito: Correcto");
		
		WebElement email = driver.findElement(By.name("your-email"));
		email.sendKeys("pedro_bacigalupohotmail.com");
		System.out.println("Mail erroneo escrito");
		test1.pass("Mail introducido erroneamente: Correcto");
		
		WebElement asunto = driver.findElement(By.name("your-subject"));
		asunto.sendKeys("Caramelos");
		System.out.println("Asunto escrito");
		test1.pass("Asunto escrito: Correcto");
		
		WebElement mensaje = driver.findElement(By.name("your-message"));
		mensaje.sendKeys("Dulce o truco");
		System.out.println("Mensaje escrito");
		test1.pass("Mensaje escrito: Correcto");
		
		WebElement enviar = driver.findElement(By.cssSelector(".wpcf7-form-control.wpcf7-submit"));
		enviar.click();
		System.out.println("Click en enviar");
		test1.pass("Click en enviar: Correcto");
		
		WebElement elegetText = driver.findElement(By.cssSelector(".wpcf7-not-valid-tip"));
		String error = elegetText.getText();
		String comp = "La dirección e-mail parece inválida.";
		test1.pass("Alerta: Detectada");
		
		if (error.equals(comp)) {
			System.out.println("Mensaje de mail erroneo detectado");
		}
		
		extent.flush();
		driver.close();
		
	}

}
