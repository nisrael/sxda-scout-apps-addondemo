/*
 * Copyright (c) 2010-2025 BSI Business Systems Integration AG
 * Copyright (c) 2023-2025 Nils Israel
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
package io.sxda.scout.apps.addondemo.client.monaco;

import io.sxda.scout.addon.monaco.client.monacofield.AbstractMonacoField;
import io.sxda.scout.apps.addondemo.shared.monaco.IMonacoService;
import io.sxda.scout.apps.addondemo.shared.monaco.MonacoFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

/**
 * Monaco demonstration form with comprehensive property configuration.
 * This form showcases all available Monaco field properties organized in tabs.
 *
 * @author nisrael
 */
@FormData(value = MonacoFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class MonacoForm extends AbstractForm {

  public MonacoForm() {
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

  public MainBox.DetailBox.MonacoField getMonacoField() {
    return getFieldByClass(MainBox.DetailBox.MonacoField.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    public class DetailBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Order(1000)
      public class MonacoField extends AbstractMonacoField {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredStatusVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridH() {
          return 10;
        }

        @Override
        protected boolean getConfiguredFillVertical() {
          return true;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 1.0;
        }

        @Override
        protected String getConfiguredLanguage() {
          return "javascript";
        }

        @Override
        protected String getConfiguredTheme() {
          return "vs-dark";
        }

        @Override
        protected boolean getConfiguredLineNumbers() {
          return true;
        }

        @Override
        protected boolean getConfiguredMinimap() {
          return true;
        }

        @Override
        protected boolean getConfiguredWordWrap() {
          return false;
        }

        @Override
        protected int getConfiguredFontSize() {
          return 14;
        }

        @Override
        protected int getConfiguredTabSize() {
          return 4;
        }

        @Override
        protected boolean getConfiguredInsertSpaces() {
          return true;
        }

        @Override
        protected boolean getConfiguredAutomaticLayout() {
          return true;
        }

        @Override
        protected boolean getConfiguredFolding() {
          return true;
        }

        @Override
        protected String getConfiguredRenderWhitespace() {
          return "none";
        }

        @Override
        protected boolean getConfiguredScrollBeyondLastLine() {
          return false;
        }

        @Override
        protected boolean getConfiguredFormatOnPaste() {
          return false;
        }

        @Override
        protected boolean getConfiguredFormatOnType() {
          return false;
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return true;
        }
      }
    }

    @Order(2000)
    public class ConfigurationBox extends AbstractTabBox {

      @Override
      protected String getConfiguredCssClass() {
        return "jswidgets-configuration";
      }

      @Order(1000)
      public class PropertiesTab extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sxda.monacoForm.propertiesTab.label");
        }

        @Order(1000)
        public class PropertiesBox extends AbstractGroupBox {

          @Override
          protected boolean getConfiguredBorderVisible() {
            return false;
          }

          @Override
          protected int getConfiguredGridColumnCount() {
            return 2;
          }

          @Order(1000)
          public class EnabledField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.enabledField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().isEnabled());
            }

            @Override
            protected void execChangedValue() {
              getMonacoField().setEnabled(getValue());
            }
          }

          @Order(2000)
          public class UpdateDisplayTextOnModifyField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.updateDisplayTextOnModifyField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().isUpdateDisplayTextOnModify());
            }

            @Override
            protected void execChangedValue() {
              getMonacoField().setUpdateDisplayTextOnModify(getValue());
            }
          }

          @Order(3000)
          public class LanguageField extends AbstractSmartField<String> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.languageField.label");
            }

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
              return MonacoLanguageLookupCall.class;
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getLanguage());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setLanguage(getValue());
              }
            }
          }

          @Order(4000)
          public class ThemeField extends AbstractSmartField<String> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.themeField.label");
            }

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
              return MonacoThemeLookupCall.class;
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getTheme());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setTheme(getValue());
              }
            }
          }

          @Order(5000)
          public class LineNumbersField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.lineNumbersField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getLineNumbers());
            }

            @Override
            protected void execChangedValue() {
              getMonacoField().setLineNumbers(getValue());
            }
          }

          @Order(6000)
          public class MinimapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.minimapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getMinimap());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setMinimap(getValue());
              }
            }
          }

          @Order(7000)
          public class WordWrapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.wordWrapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getWordWrap());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setWordWrap(getValue());
              }
            }
          }

          @Order(8000)
          public class FoldingField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.foldingField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getFolding());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setFolding(getValue());
              }
            }
          }

          @Order(9000)
          public class FontSizeField extends AbstractIntegerField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.fontSizeField.label");
            }

            @Override
            protected Integer getConfiguredMinValue() {
              return 8;
            }

            @Override
            protected Integer getConfiguredMaxValue() {
              return 32;
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getFontSize());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setFontSize(getValue());
              }
            }
          }

          @Order(10000)
          public class TabSizeField extends AbstractIntegerField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.tabSizeField.label");
            }

            @Override
            protected Integer getConfiguredMinValue() {
              return 1;
            }

            @Override
            protected Integer getConfiguredMaxValue() {
              return 8;
            }

            @Override
            protected void execInitField() {
              setValue(getMonacoField().getTabSize());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getMonacoField().setTabSize(getValue());
              }
            }
          }

          @Order(11000)
          public class SetValueFieldBox extends AbstractSequenceBox {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.monacoForm.setValueFieldBox.label");
            }

            @Override
            protected int getConfiguredGridW() {
              return 2;
            }

            @Order(1000)
            public class SetValueField extends AbstractStringField {
              @Override
              protected boolean getConfiguredLabelVisible() {
                return false;
              }

              @Override
              protected int getConfiguredGridW() {
                return FULL_WIDTH;
              }
            }

            @Order(2000)
            public class SetValueButton extends AbstractButton {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("sxda.monacoForm.setValueButton.label");
              }

              @Override
              protected int getConfiguredDisplayStyle() {
                return DISPLAY_STYLE_DEFAULT;
              }

              @Override
              protected void execClickAction() {
                String value = getSetValueField().getValue();
                if (value != null) {
                  getMonacoField().setValue(value);
                }
              }
            }

            public SetValueField getSetValueField() {
              return getFieldByClass(SetValueField.class);
            }
          }
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      IMonacoService service = BEANS.get(IMonacoService.class);
      MonacoFormData formData = new MonacoFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
