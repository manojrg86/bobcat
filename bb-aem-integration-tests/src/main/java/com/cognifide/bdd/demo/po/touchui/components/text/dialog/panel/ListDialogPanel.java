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
package com.cognifide.bdd.demo.po.touchui.components.text.dialog.panel;

import com.cognifide.bdd.demo.po.touchui.GeometrixxFieldTypes;
import com.cognifide.bdd.demo.po.touchui.dialog.text.ControlToolbar;
import com.cognifide.bdd.demo.po.touchui.dialog.text.ListControls;
import com.cognifide.qa.bb.aem.pageobjects.touchui.dialogfields.DialogField;
import com.cognifide.qa.bb.constants.Timeouts;
import com.cognifide.qa.bb.provider.selenium.BobcatWait;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

@PageObject
public class ListDialogPanel implements DialogField {

  @FindBy(css = ".coral-RichText-toolbar")
  private ControlToolbar controlToolbar;

  @FindBy(css = ".coral-Popover-content")
  private ListControls listControl;

  @Inject
  private BobcatWait bobcatWait;

  @Override
  public void setValue(Object value) {
    String actionText = (String) value;
    ListPanelActions action = ListPanelActions.valueOf(actionText.toUpperCase());

    switch (action) {
      case NUMBERED:
        openListPopover();
        listControl.getNumberListBtn().click();
        break;
      case BULLET:
        openListPopover();
        listControl.getBulletListBtn().click();
        break;
      case INDENT:
        openListPopover();
        listControl.getIndentListBtn().click();
        break;
      case OUTDENT:
        openListPopover();
        listControl.getOutdentListBtn().click();
        break;
      default:
        throw new IllegalArgumentException("There is no action defined for " + actionText);
    }
  }

  private void openListPopover() {
    controlToolbar.selectText();
    bobcatWait.withTimeout(Timeouts.SMALL).until((ExpectedCondition<Object>) input -> controlToolbar.
            getToggleListButton().isEnabled());
    controlToolbar.getToggleListButton().click();
  }

  @Override
  public String getType() {
    return GeometrixxFieldTypes.LIST.name();
  }

  private static enum ListPanelActions {

    NUMBERED,
    BULLET,
    INDENT,
    OUTDENT;
  }

}
