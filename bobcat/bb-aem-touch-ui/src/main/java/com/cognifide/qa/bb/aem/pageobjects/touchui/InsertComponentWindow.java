/*-
 * #%L
 * Bobcat Parent
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
package com.cognifide.qa.bb.aem.pageobjects.touchui;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.aem.util.Conditions;
import com.google.inject.Inject;

@PageObject
public class InsertComponentWindow {

  public static final String CSS = ".InsertComponentDialog";

  @Inject
  private Conditions conditions;

  @Inject
  @CurrentScope
  private WebElement window;

  @FindBy(css = "button.InsertComponentDialog-component")
  private List<WebElement> components;

  @FindBy(css = ".coral-Modal-closeButton")
  private WebElement closeButton;

  public void insertComponent(String title) {
    conditions.elementReady(getComponent(title)).click();
    conditions.verify(not(visibilityOf(window)));
  }

  /**
   * This check contains a workaroung for an issue in AEM 6.1, when after the deploy occasionally Insert
   * Window contains an empty list.
   *
   * @return true if window is displayed AND it contains some components
   */
  public boolean isDisplayedExpectingComponents() {
    boolean result = false;
    if (conditions.isConditionMet(visibilityOf(window))) {
      if (components.isEmpty()) {
        closeButton.click();
      } else {
        result = true;
      }
    }
    return result;
  }

  private WebElement getComponent(String name) {
    return components.stream() //
            .filter(element -> equalsIgnoreCase(element.getText(), name)) //
            .findFirst() //
            .orElseThrow(
                    () -> new IllegalStateException("Specified component is not available: " + name));
  }
}