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
package io.sxda.scout.apps.addondemo.client.ace;

import io.sxda.scout.addon.ace.client.acefield.AbstractAceField;
import io.sxda.scout.addon.ace.client.acefield.AceMode;
import io.sxda.scout.addon.ace.client.acefield.AceModeLookupCall;
import io.sxda.scout.addon.ace.client.acefield.AceTheme;
import io.sxda.scout.addon.ace.client.acefield.AceThemeLookupCall;
import io.sxda.scout.apps.addondemo.shared.ace.AceFormData;
import io.sxda.scout.apps.addondemo.shared.ace.IAceService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
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
 * Ace demonstration form with comprehensive property configuration.
 * This form showcases all available Ace field properties organized in tabs.
 *
 * @author nisrael
 */
@FormData(value = AceFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AceForm extends AbstractForm {

  public AceForm() {
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

  public MainBox.DetailBox.AceField getAceField() {
    return getFieldByClass(MainBox.DetailBox.AceField.class);
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
      public class AceField extends AbstractAceField {

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
        protected boolean getConfiguredShowPrintMargin() {
          return true;
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
        protected boolean getConfiguredUseSoftTabs() {
          return true;
        }

        @Override
        protected boolean getConfiguredUseWrapMode() {
          return false;
        }

        @Override
        protected String getConfiguredTheme() {
          return AceTheme.TWILIGHT.getConfigTerm();
        }

        @Override
        protected String getConfiguredAceMode() {
          return AceMode.JAVA.getConfigTerm();
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
          return TEXTS.get("sxda.aceForm.propertiesTab.label");
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
              return TEXTS.get("sxda.aceForm.enabledField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().isEnabled());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setEnabled(getValue());
            }
          }

          @Order(2000)
          public class UpdateDisplayTextOnModifyField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.updateDisplayTextOnModifyField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().isUpdateDisplayTextOnModify());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setUpdateDisplayTextOnModify(getValue());
            }
          }

          @Order(3000)
          public class ThemeField extends AbstractSmartField<String> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.themeField.label");
            }

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
              return AceThemeLookupCall.class;
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getTheme());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getAceField().setTheme(getValue());
              }
            }
          }

          @Order(4000)
          public class ModeField extends AbstractSmartField<String> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.modeField.label");
            }

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
              return AceModeLookupCall.class;
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getAceMode());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getAceField().setAceMode(getValue());
              }
            }
          }

          @Order(5000)
          public class SoftTabsField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.softTabsField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getUseSoftTabs());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setUseSoftTabs(getValue());
            }
          }

          @Order(6000)
          public class WrapModeField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.wrapModeField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getUseWrapMode());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setUseWrapMode(getValue());
            }
          }

          @Order(7000)
          public class ShowPrintMarginField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.showPrintMarginField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getShowPrintMargin());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setShowPrintMargin(getValue());
            }
          }

          @Order(8000)
          public class HighlightActiveLineField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.highlightActiveLineField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getHighlightActiveLine());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setHighlightActiveLine(getValue());
            }
          }

          @Order(9000)
          public class SelectOnSetValueField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.selectOnSetValueField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getAceField().getSelectOnSetValue());
            }

            @Override
            protected void execChangedValue() {
              getAceField().setSelectOnSetValue(getValue());
            }
          }

          @Order(10000)
          public class SetValueFieldBox extends AbstractSequenceBox {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.aceForm.setValueFieldBox.label");
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
                return TEXTS.get("sxda.aceForm.setValueButton.label");
              }

              @Override
              protected int getConfiguredDisplayStyle() {
                return DISPLAY_STYLE_DEFAULT;
              }

              @Override
              protected void execClickAction() {
                String value = getSetValueField().getValue();
                if (value != null) {
                  getAceField().setValue(value);
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
      IAceService service = BEANS.get(IAceService.class);
      AceFormData formData = new AceFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
