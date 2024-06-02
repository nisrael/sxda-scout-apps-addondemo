package io.sxda.scout.apps.addondemo.client.ace;

import io.sxda.scout.addon.ace.client.acefield.*;
import io.sxda.scout.apps.addondemo.shared.ace.AceFormData;
import io.sxda.scout.apps.addondemo.shared.ace.IAceService;
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

  public MainBox.TopBox getTopBox() {
    return getFieldByClass(MainBox.TopBox.class);
  }

  public MainBox.TopBox.AceField getAceField() {
    return getFieldByClass(MainBox.TopBox.AceField.class);
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
        return TEXTS.get("sxda.aceForm.toggleEnabledButton.label");
      }

      @Override
      protected void execClickAction() {
        getAceField().setEnabled(!getAceField().isEnabled());
      }
    }

    @Order(2000)
    public class ToggleHighlightActiveLineButton extends AbstractButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("sxda.aceForm.toggleHighlightActiveLineButton.label");
      }

      @Override
      protected void execClickAction() {
        getAceField().setHighlightActiveLine(!getAceField().getHighlightActiveLine());
      }
    }

    @Order(3000)
    public class ToggleShowPrintMarginButton extends AbstractButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("sxda.aceForm.toggleShowPrintMarginButton.label");
      }

      @Override
      protected void execClickAction() {
        getAceField().setShowPrintMargin(!getAceField().getShowPrintMargin());
      }
    }

    @Order(4000)
    public class ThemeButton extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("sxda.aceForm.themeButton.label");
      }

      @Override
      protected void execInitAction() {
        AceThemeLookupCall lookupCall = new AceThemeLookupCall();
        List<? extends ILookupRow<String>> rows = lookupCall.getDataByAll();
        for (ILookupRow<String> row : rows) {
          AbstractMenu menu = new AbstractMenu() {
            @Override
            protected String getConfiguredText() {
              return row.getText();
            }

            @Override
            protected void execAction() {
              getAceField().setTheme(row.getKey());
            }
          };
          getMenuByClass(ThemeButton.class).addChildAction(menu);
        }
      }
    }

    @Order(4500)
    public class ModeButton extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("sxda.aceForm.modeButton.label");
      }

      @Override
      protected void execInitAction() {
        AceModeLookupCall lookupCall = new AceModeLookupCall();
        List<? extends ILookupRow<String>> rows = lookupCall.getDataByAll();
        for (ILookupRow<String> row : rows) {
          AbstractMenu menu = new AbstractMenu() {
            @Override
            protected String getConfiguredText() {
              return row.getText();
            }

            @Override
            protected void execAction() {
              getAceField().setAceMode(row.getKey());
            }
          };
          getMenuByClass(ModeButton.class).addChildAction(menu);
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
        return TEXTS.get("sxda.aceForm.title");
      }


      @Order(1000)
      public class AceField extends AbstractAceField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sxda.aceForm.aceField.label");
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

      public class ContentsField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sxda.aceForm.contentsField.label");
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
          return AceField.class;
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
      IAceService service = BEANS.get(IAceService.class);
      AceFormData formData = new AceFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
