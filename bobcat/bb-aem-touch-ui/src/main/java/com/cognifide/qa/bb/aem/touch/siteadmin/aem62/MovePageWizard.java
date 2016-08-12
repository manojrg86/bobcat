/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.qa.bb.aem.touch.siteadmin.aem62;

import com.cognifide.qa.bb.provider.selenium.BobcatWait;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageObject
public class MovePageWizard {

  @Inject
  WebDriver driver;

  @Inject
  private BobcatWait wait;

  @FindBy(css = "button.coral-Button--primary[data-foundation-wizard-control-action='next']")
  private WebElement nextButton;

  @FindBy(css = "coral-panel.is-selected button.coral-Button--primary[data-foundation-wizard-control-action='next']")
  private WebElement moveButton;

  @FindBy(css = "coral-columnview-item-thumbnail")
  private WebElement anyPage;

  public MovePageWizard next() {
    nextButton.click();
    return this;
  }

  public MovePageWizard overrideDestinationPage(String destination) {
    anyPage.click();
    BobcatWait.sleep(1);
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("document.getElementsByClassName('foundation-advancedselect-values')[0].removeAttribute('hidden');");
    jse.executeScript("document.getElementsByName('destPath')[0].setAttribute('type', 'text');");
    driver.findElement(By.xpath("//input[@name='destPath']")).clear();
    driver.findElement(By.xpath("//input[@name='destPath']")).sendKeys(destination);
    return this;
  }

  public MovePageWizard move() {
    moveButton.click();
    return this;
  }

}
