/*
 * Copyright (c) 2010-20250906-180705 BSI Business Systems Integration AG
 * Copyright (c) 2023-20250906-180705 Nils Israel
 *
 * This program is an extension of the original work from the Eclipse Scout Project,
 * available at https://www.eclipse.org/scout/.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package io.sxda.scout.apps.addondemo.client.codemirror;

import io.sxda.scout.addon.codemirror.client.codemirrorfield.AbstractCodeMirrorField;
import io.sxda.scout.addon.codemirror.client.codemirrorfield.CodeMirrorLanguageLookupCall;
import io.sxda.scout.addon.codemirror.client.codemirrorfield.CodeMirrorTheme;
import io.sxda.scout.addon.codemirror.client.codemirrorfield.CodeMirrorThemeLookupCall;
import io.sxda.scout.apps.addondemo.shared.ace.IAceService;
import io.sxda.scout.apps.addondemo.shared.codemirror.CodeMirrorFormData;
import io.sxda.scout.apps.addondemo.shared.codemirror.ICodeMirrorService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import java.util.List;

/**
 * @author nisrael
 */
@FormData(value = CodeMirrorFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class CodeMirrorForm extends AbstractForm {

  public CodeMirrorForm() {
    setHandler(new ViewHandler());
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredModalityHint() {
    return MODALITY_HINT_MODELESS;
  }

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.World;
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MainBox.TopBox getTopBox() {
    return getFieldByClass(MainBox.TopBox.class);
  }

  public MainBox.TopBox.CodeMirrorField getCodeMirrorField() {
    return getFieldByClass(MainBox.TopBox.CodeMirrorField.class);
  }

  public MainBox.TopBox.ContentsField getContentsField() {
    return getFieldByClass(MainBox.TopBox.ContentsField.class);
  }

  public MainBox.ToggleEnabledButton getToggleEnabledButton() {
    return getFieldByClass(MainBox.ToggleEnabledButton.class);
  }

  public MainBox.ToggleHighlightActiveLineButton getToggleHighlightActiveLineButton() {
    return getFieldByClass(MainBox.ToggleHighlightActiveLineButton.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    public class ToggleEnabledButton extends AbstractButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("sxda.codeMirrorForm.toggleEnabledButton.label");
      }

      @Override
      protected void execClickAction() {
        getCodeMirrorField().setEnabled(!getCodeMirrorField().isEnabled());
      }
    }

    @Order(2000)
    public class ToggleHighlightActiveLineButton extends AbstractButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("sxda.codeMirrorForm.toggleHighlightActiveLineButton.label");
      }

      @Override
      protected void execClickAction() {
        getCodeMirrorField().setHighlightActiveLine(!getCodeMirrorField().getHighlightActiveLine());
      }
    }

    @Order(4000)
    public class ThemeButton extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("sxda.codeMirrorForm.themeButton.label");
      }

      @Override
      protected void execInitAction() {
        CodeMirrorThemeLookupCall lookupCall = new CodeMirrorThemeLookupCall();
        List<? extends ILookupRow<String>> rows = lookupCall.getDataByAll();
        for (ILookupRow<String> row : rows) {
          AbstractMenu menu = new AbstractMenu() {
            @Override
            protected String getConfiguredText() {
              return row.getText();
            }

            @Override
            protected void execAction() {
              getCodeMirrorField().setTheme(row.getKey());
            }
          };
          getMenuByClass(ThemeButton.class).addChildAction(menu);
        }
      }
    }

    @Order(4500)
    public class LanguageButton extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("sxda.codeMirrorForm.modeButton.label");
      }

      @Override
      protected void execInitAction() {
        CodeMirrorLanguageLookupCall lookupCall = new CodeMirrorLanguageLookupCall();
        List<? extends ILookupRow<String>> rows = lookupCall.getDataByAll();
        for (ILookupRow<String> row : rows) {
          AbstractMenu menu = new AbstractMenu() {
            @Override
            protected String getConfiguredText() {
              return row.getText();
            }

            @Override
            protected void execAction() {
              getCodeMirrorField().setLanguage(row.getKey());
            }
          };
          getMenuByClass(LanguageButton.class).addChildAction(menu);
        }
      }
    }

    @Order(1000)
    public class TopBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("sxda.codeMirrorForm.title");
      }


      @Order(1000)
      public class CodeMirrorField extends AbstractCodeMirrorField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sxda.codeMirrorForm.CodeMirrorField.label");
        }

        @Override
        protected boolean getConfiguredFillVertical() {
          return true;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 0.75;
        }

        @Override
        protected int getConfiguredTabSize() {
          return 2;
        }

        @Override
        protected boolean getConfiguredHighlightActiveLine() {
          return true;
        }

        @Override
        protected String getConfiguredTheme() {
          return CodeMirrorTheme.AURA.getConfigTerm();
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return true;
        }
      }

      public class ContentsField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sxda.codeMirrorForm.contentsField.label");
        }

        @Override
        protected boolean getConfiguredFillVertical() {
          return true;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 0.25;
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected Class<? extends IValueField<?>> getConfiguredMasterField() {
          return CodeMirrorField.class;
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return true;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) {
          if (newMasterValue instanceof String stringValue) {
            setValue(stringValue);
          }
        }

        @Override
        protected void execInitField() {
          if (getMasterField().getValue() instanceof String stringValue) {
            setValue(stringValue);
          }
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      ICodeMirrorService service = BEANS.get(ICodeMirrorService.class);
      CodeMirrorFormData formData = new CodeMirrorFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
