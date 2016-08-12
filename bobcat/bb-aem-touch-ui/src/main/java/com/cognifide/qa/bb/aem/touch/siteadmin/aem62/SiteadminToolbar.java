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

import java.time.LocalDateTime;

import com.cognifide.qa.bb.qualifier.Global;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@PageObject
public class SiteadminToolbar {

  @Inject
  private WebDriver driver;

  @FindBy(css = "button[icon='delete']")
  private WebElement deleteButton;

  @FindBy(css = "button[icon='copy']")
  private WebElement copyButton;

  @FindBy(css = "button[icon='globe']")
  private WebElement publishButton;

  @FindBy(css = "button[icon='globeClock']")
  private WebElement publishLaterButton;

  @FindBy(css = "button[icon='globeStrike']")
  private WebElement unpublishButton;

  @FindBy(css = "button[icon='globeStrikeClock']")
  private WebElement unpublishLaterButton;

  @FindBy(css = "button[icon='move']")
  private WebElement moveButton;

  @Global
  @FindBy(css = "coral-dialog.coral-Dialog.is-open")
  private WebElement dialog;

  @Global
  @FindBy(css = "form.cq-siteadmin-admin-publishpage-form")
  private PublishPageWizard publishPageWizard;

  @Global
  @FindBy(css = "form.cq-siteadmin-admin-unpublishpage-form")
  private PublishPageWizard unpublishPageWizard;

  @Global
  @FindBy(css = "form#cq-siteadmin-admin-movepage-form")
  private MovePageWizard movePageWizard;

  public SiteadminToolbar deleteSelectedPages() {
    deleteButton.click();
    dialog.findElement(By.cssSelector("button.coral-Button--warning")).click();
    return this;
  }

  public SiteadminToolbar publishPageNow() {
    publishButton.click();
    return this;
  }

  public SiteadminToolbar unpublishPageNow() {
    unpublishButton.click();
    return this;
  }

  public SiteadminToolbar publishPageLater(LocalDateTime scheduledTime) {
    publishLaterButton.click();
    publishPageWizard.selectDateAndTime(scheduledTime);
    publishPageWizard.submit();
    return this;
  }

  public SiteadminToolbar unpublishPageLater(LocalDateTime scheduledTime) {
    unpublishLaterButton.click();
    unpublishPageWizard.selectDateAndTime(scheduledTime);
    unpublishPageWizard.submit();
    return this;
  }

  public SiteadminToolbar movePage(String destination) {
    moveButton.click();
    //no renaming
    movePageWizard.next().overrideDestinationPage(destination).move();
    return this;
  }

  public SiteadminToolbar copyPage() {
    copyButton.click();
    return this;
  }
}
