/*
 * Copyright 2016 Cognifide Ltd..
 *
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
 */
package com.cognifide.qa.bb.aem.touch.siteadmin.aem61;

import com.cognifide.qa.bb.aem.touch.siteadmin.aem61.calendar.GraniteCalendar;
import com.cognifide.qa.bb.constants.Timeouts;
import com.cognifide.qa.bb.provider.selenium.BobcatWait;
import com.cognifide.qa.bb.qualifier.Global;
import com.cognifide.qa.bb.qualifier.PageObject;

import java.time.LocalDateTime;
import java.util.Objects;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageObject
public class ReplicateLaterWindow {

  @Inject
  private BobcatWait wait;

  @Global
  @FindBy(css = "button.coral-Wizard-nextButton[type='submit']")
  private WebElement submitButton;

  @FindBy(css = "button.coral-Button.coral-Button--square")
  private WebElement calendarButton;

  @Global
  @FindBy(css = ".coral-Popover--datepicker")
  private GraniteCalendar calendar;

  public void selectDateAndTime(LocalDateTime dateTime) {
    if (dateTime.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("Selected date and time should be placed in future");
    }

    calendarButton.click();
    wait.withTimeout(Timeouts.MEDIUM).until(input ->
        calendar.isDisplayed()
    );
    calendar.selectDateAndTime(dateTime);
    wait.withTimeout(Timeouts.SMALL).until(input -> Objects.isNull(submitButton.getAttribute("disabled")), Timeouts.MINIMAL);
    calendar.closeCalendar();
  }

  public void submit() {
    submitButton.click();
  }

}
