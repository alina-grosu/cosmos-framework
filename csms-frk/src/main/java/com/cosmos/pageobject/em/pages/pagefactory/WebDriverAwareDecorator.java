package com.cosmos.pageobject.em.pages.pagefactory;

import static ru.yandex.qatools.htmlelements.loader.HtmlElementLoader.createHtmlElement;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.getElementName;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.newInstance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.cosmos.pageobject.em.pages.pagecomponents.IWebDriverAware;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

public class WebDriverAwareDecorator extends HtmlElementDecorator {

	private WebDriver driver;

	public WebDriverAwareDecorator(CustomElementLocatorFactory factory, WebDriver driver) {
        super(factory);           
        this.driver = driver;
    }

    
	@Override
    protected <T extends HtmlElement> T decorateHtmlElement(ClassLoader loader, Field field) {
            	    	
    	WebElement elementToWrap = decorateWebElement(loader, field);
    	@SuppressWarnings("unchecked")
		T element = createHtmlElement((Class<T>) field.getType(), elementToWrap, getElementName(field));
    	
        if (element instanceof IWebDriverAware) {
            ((IWebDriverAware)element).setWebDriver(driver);
        }

        return element;
    }
    
    private <T extends HtmlElement> T createHtmlElement(Class<T> elementClass, WebElement elementToWrap, String name) 
    {
    	try 
    	{
    		T instance = newInstance(elementClass);
    		instance.setWrappedElement(elementToWrap);
    		instance.setName(name);
    		// Recursively initialize elements of the block    		
    		WebDriverAwareDecorator webDriverAwareDecorator = 
    				new WebDriverAwareDecorator(new HtmlElementLocatorFactory(instance), driver);
    		PageFactory.initElements(webDriverAwareDecorator, instance);
    		    		
    		return instance;
    	} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) 
    	{
    		throw new HtmlElementsException(e);
    	}
    }       

}
