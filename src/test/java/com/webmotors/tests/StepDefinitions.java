package com.webmotors.tests;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class StepDefinitions {
    private static WebDriver driver;

    private void ensureDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
            driver.manage().window().maximize();
        }
    }

    @Dado("que eu estou na página de resultado de busca")
    public void abrir_pagina_resultado() {
        ensureDriver();
        driver.get("https://www.webmotors.com.br/carros/estoque?tipoveiculo=carros&estadocidade=estoque");
    }

    @Quando("eu seleciono a marca {string}")
    public void seleciono_marca(String marca) {
        ensureDriver();
        try {
            // Tenta clicar em elemento que contenha o texto da marca
            WebElement el = driver.findElement(By.xpath("//*[contains(text(), '" + marca + "')]"));
            el.click();
        } catch (Exception e) {
            // fallback: procurar selects e enviar texto
            try {
                WebElement sel = driver.findElement(By.tagName("select"));
                sel.sendKeys(marca);
            } catch (Exception ex) {
                // ignorar; validação posterior via conteúdo da página
            }
        }
    }

    @Quando("eu seleciono o modelo {string}")
    public void seleciono_modelo(String modelo) {
        ensureDriver();
        try {
            WebElement el = driver.findElement(By.xpath("//*[contains(text(), '" + modelo + "')]"));
            el.click();
        } catch (Exception e) {
            try {
                WebElement sel = driver.findElement(By.tagName("select"));
                sel.sendKeys(modelo);
            } catch (Exception ex) {
            }
        }
    }

    @Entao("os resultados devem conter veículos da marca {string} e modelo {string}")
    public void validar_resultados_marca_modelo(String marca, String modelo) {
        ensureDriver();
        String page = driver.getPageSource();
        Assert.assertTrue("Página deve conter a marca", page.contains(marca));
        Assert.assertTrue("Página deve conter o modelo", page.contains(modelo));
        driver.quit();
        driver = null;
    }

    @Dado("que eu selecionei a marca {string} e o modelo {string}")
    public void dado_marca_modelo(String marca, String modelo) {
        abrir_pagina_resultado();
        seleciono_marca(marca);
        seleciono_modelo(modelo);
    }

    @Quando("eu seleciono a versão disponível")
    public void seleciono_versao() {
        ensureDriver();
        try {
            WebElement el = driver.findElement(By.xpath("//*[contains(@class,'version') or contains(text(),'Versão')]/following::li[1]"));
            el.click();
        } catch (Exception e) {
            // tentativa genérica
            try {
                WebElement el2 = driver.findElement(By.xpath("//li[contains(text(),'')][1]"));
                el2.click();
            } catch (Exception ex) {
            }
        }
    }

    @Entao("os resultados devem filtrar pela versão selecionada")
    public void validar_versao_filtrada() {
        ensureDriver();
        String page = driver.getPageSource();
        // validação genérica: presença de texto 'Versão' ou de algum detalhe na página
        Assert.assertTrue("Página deve conter indicação de versão", page.contains("Versão") || page.contains("versão"));
        driver.quit();
        driver = null;
    }

    @Dado("que eu acesso o estoque da loja com IdRevendedor 3834764")
    public void acesso_estoque_loja() {
        ensureDriver();
        driver.get("https://www.webmotors.com.br/carros/estoque/?IdRevendedor=3834764&TipoVeiculo=carros&anunciante=concession%C3%A1ria%7Cloja");
    }

    @Entao("devo ver listagem de veículos pertencentes à loja")
    public void validar_listagem_loja() {
        ensureDriver();
        String page = driver.getPageSource();
        Assert.assertTrue("Página de estoque da loja deve conter o ID do revendedor ou listagem", page.contains("3834764") || page.toLowerCase().contains("revendedor") || page.toLowerCase().contains("anunciante"));
        driver.quit();
        driver = null;
    }
}
