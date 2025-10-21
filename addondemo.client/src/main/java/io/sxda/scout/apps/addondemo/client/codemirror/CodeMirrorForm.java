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
package io.sxda.scout.apps.addondemo.client.codemirror;

import io.sxda.scout.addon.codemirror.client.codemirrorfield.AbstractCodeMirrorField;
import io.sxda.scout.addon.codemirror.client.codemirrorfield.CodeMirrorLanguageLookupCall;
import io.sxda.scout.addon.codemirror.client.codemirrorfield.CodeMirrorTheme;
import io.sxda.scout.addon.codemirror.client.codemirrorfield.CodeMirrorThemeLookupCall;
import io.sxda.scout.apps.addondemo.shared.codemirror.CodeMirrorFormData;
import io.sxda.scout.apps.addondemo.shared.codemirror.ICodeMirrorService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
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
 * CodeMirror demonstration form with comprehensive property configuration.
 * This form showcases all available CodeMirror field properties organized in tabs.
 *
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

  public MainBox.DetailBox.CodeMirrorField getCodeMirrorField() {
    return getFieldByClass(MainBox.DetailBox.CodeMirrorField.class);
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
      public class CodeMirrorField extends AbstractCodeMirrorField {

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
          return TEXTS.get("sxda.codeMirrorForm.propertiesTab.label");
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
              return TEXTS.get("sxda.codeMirrorForm.enabledField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().isEnabled());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setEnabled(getValue());
            }
          }

          @Order(2000)
          public class UpdateDisplayTextOnModifyField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.updateDisplayTextOnModifyField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().isUpdateDisplayTextOnModify());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setUpdateDisplayTextOnModify(getValue());
            }
          }

          @Order(3000)
          public class LanguageField extends AbstractSmartField<String> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.languageField.label");
            }

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
              return CodeMirrorLanguageLookupCall.class;
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getLanguage());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getCodeMirrorField().setLanguage(getValue());
              }
            }
          }

          @Order(4000)
          public class ThemeField extends AbstractSmartField<String> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.themeField.label");
            }

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
              return CodeMirrorThemeLookupCall.class;
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getTheme());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getCodeMirrorField().setTheme(getValue());
              }
            }
          }

          @Order(5000)
          public class SyntaxHighlightingField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.syntaxHighlightingField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getSyntaxHighlighting());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setSyntaxHighlighting(getValue());
            }
          }

          @Order(6000)
          public class HighlightActiveLineField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.highlightActiveLineField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getHighlightActiveLine());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setHighlightActiveLine(getValue());
            }
          }

          @Order(7000)
          public class TabSizeField extends AbstractIntegerField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.tabSizeField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getTabSize());
            }

            @Override
            protected void execChangedValue() {
              if (getValue() != null) {
                getCodeMirrorField().setTabSize(getValue());
              }
            }
          }

          @Order(8000)
          public class LineNumbersField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.lineNumbersField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getLineNumbers());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setLineNumbers(getValue());
            }
          }

          @Order(9000)
          public class HighlightActiveLineGutterField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.highlightActiveLineGutterField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getHighlightActiveLineGutter());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setHighlightActiveLineGutter(getValue());
            }
          }

          @Order(10000)
          public class FoldGutterField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.foldGutterField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getFoldGutter());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setFoldGutter(getValue());
            }
          }

          @Order(11000)
          public class DropCursorField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.dropCursorField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getDropCursor());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setDropCursor(getValue());
            }
          }

          @Order(12000)
          public class AllowMultipleSelectionsField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.allowMultipleSelectionsField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getAllowMultipleSelections());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setAllowMultipleSelections(getValue());
            }
          }

          @Order(13000)
          public class IndentOnInputField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.indentOnInputField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getIndentOnInput());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setIndentOnInput(getValue());
            }
          }

          @Order(14000)
          public class BracketMatchingField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.bracketMatchingField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getBracketMatching());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setBracketMatching(getValue());
            }
          }

          @Order(15000)
          public class CloseBracketsField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.closeBracketsField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getCloseBrackets());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setCloseBrackets(getValue());
            }
          }

          @Order(16000)
          public class AutocompletionField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.autocompletionField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getAutocompletion());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setAutocompletion(getValue());
            }
          }

          @Order(17000)
          public class RectangularSelectionField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.rectangularSelectionField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getRectangularSelection());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setRectangularSelection(getValue());
            }
          }

          @Order(18000)
          public class CrosshairCursorField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.crosshairCursorField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getCrosshairCursor());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setCrosshairCursor(getValue());
            }
          }

          @Order(19000)
          public class HighlightSelectionMatchesField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.highlightSelectionMatchesField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getHighlightSelectionMatches());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setHighlightSelectionMatches(getValue());
            }
          }

          @Order(20000)
          public class CloseBracketsKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.closeBracketsKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getCloseBracketsKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setCloseBracketsKeymap(getValue());
            }
          }

          @Order(21000)
          public class SearchKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.searchKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getSearchKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setSearchKeymap(getValue());
            }
          }

          @Order(22000)
          public class FoldKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.foldKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getFoldKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setFoldKeymap(getValue());
            }
          }

          @Order(23000)
          public class CompletionKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.completionKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getCompletionKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setCompletionKeymap(getValue());
            }
          }

          @Order(24000)
          public class LintKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.lintKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getLintKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setLintKeymap(getValue());
            }
          }

          @Order(25000)
          public class HighlightSpecialCharsField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.highlightSpecialCharsField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getHighlightSpecialChars());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setHighlightSpecialChars(getValue());
            }
          }

          @Order(26000)
          public class HistoryField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.historyField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getHistory());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setHistory(getValue());
            }
          }

          @Order(27000)
          public class DrawSelectionField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.drawSelectionField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getDrawSelection());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setDrawSelection(getValue());
            }
          }

          @Order(28000)
          public class DefaultKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.defaultKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getDefaultKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setDefaultKeymap(getValue());
            }
          }

          @Order(29000)
          public class HistoryKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.historyKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getHistoryKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setHistoryKeymap(getValue());
            }
          }

          @Order(30000)
          public class IndentWithTabKeymapField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.indentWithTabKeymapField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getIndentWithTabKeymap());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setIndentWithTabKeymap(getValue());
            }
          }

          @Order(31000)
          public class LineWrappingField extends AbstractBooleanField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.lineWrappingField.label");
            }

            @Override
            protected void execInitField() {
              setValue(getCodeMirrorField().getLineWrapping());
            }

            @Override
            protected void execChangedValue() {
              getCodeMirrorField().setLineWrapping(getValue());
            }
          }

          @Order(32000)
          public class SetValueFieldBox extends AbstractSequenceBox {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("sxda.codeMirrorForm.setValueFieldBox.label");
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
                return TEXTS.get("sxda.codeMirrorForm.setValueButton.label");
              }

              @Override
              protected int getConfiguredDisplayStyle() {
                return DISPLAY_STYLE_DEFAULT;
              }

              @Override
              protected void execClickAction() {
                String value = getSetValueField().getValue();
                if (value != null) {
                  getCodeMirrorField().setValue(value);
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
      ICodeMirrorService service = BEANS.get(ICodeMirrorService.class);
      CodeMirrorFormData formData = new CodeMirrorFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
