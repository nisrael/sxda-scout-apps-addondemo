package io.sxda.scout.apps.addondemo.client.helloworld;

import io.sxda.scout.apps.addondemo.client.helloworld.HelloWorldForm.MainBox.TopBox;
import io.sxda.scout.apps.addondemo.client.helloworld.HelloWorldForm.MainBox.TopBox.MessageField;
import io.sxda.scout.apps.addondemo.shared.helloworld.HelloWorldFormData;
import io.sxda.scout.apps.addondemo.shared.helloworld.IHelloWorldService;
import org.eclipse.scout.rt.chart.client.ui.basic.chart.AbstractChart;
import org.eclipse.scout.rt.chart.client.ui.form.fields.chartfield.AbstractChartField;
import org.eclipse.scout.rt.chart.shared.data.basic.chart.ChartAxisBean;
import org.eclipse.scout.rt.chart.shared.data.basic.chart.ChartData;
import org.eclipse.scout.rt.chart.shared.data.basic.chart.IChartAxisBean;
import org.eclipse.scout.rt.chart.shared.data.basic.chart.MonupleChartValueGroupBean;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author nisrael
 */
@FormData(value = HelloWorldFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class HelloWorldForm extends AbstractForm {

  public HelloWorldForm() {
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

  public TopBox getTopBox() {
    return getFieldByClass(TopBox.class);
  }

  public MessageField getMessageField() {
    return getFieldByClass(MessageField.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    public class TopBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("MessageFromServer");
      }

      @Order(1000)
      public class MessageField extends AbstractStringField {
        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Message");
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }
      }

      @Order(2000)
      public class ChartBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ChartBox.label");
        }

        @Override
        protected boolean getConfiguredFillVertical() {
          return true;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 1.0;
        }

        public class ChartField extends AbstractChartField<ChartField.Chart> {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Chart");
          }

          @Override
          protected boolean getConfiguredFillVertical() {
            return true;
          }

          @Override
          protected double getConfiguredGridWeightY() {
            return 1.0;
          }

          public class Chart extends AbstractChart {
          }
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      IHelloWorldService service = BEANS.get(IHelloWorldService.class);
      HelloWorldFormData formData = new HelloWorldFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

      ChartData data = new ChartData();

      List<IChartAxisBean> axis = new ArrayList<>();
      Stream.of("Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec.")
        .forEach(label -> axis.add(new ChartAxisBean(label, label)));

      data.getAxes().add(axis);

      MonupleChartValueGroupBean vanilla = new MonupleChartValueGroupBean();
      vanilla.setGroupName("Vanilla");
      IntStream.of(0, 0, 0, 94, 162, 465, 759, 537, 312, 106, 0, 0)
        .forEach(value -> vanilla.getValues().add(new BigDecimal(value)));
      data.getChartValueGroups().add(vanilla);

      MonupleChartValueGroupBean chocolate = new MonupleChartValueGroupBean();
      chocolate.setGroupName("Chocolate");
      IntStream.of(0, 0, 0, 81, 132, 243, 498, 615, 445, 217, 0, 0)
        .forEach(value -> chocolate.getValues().add(new BigDecimal(value)));
      data.getChartValueGroups().add(chocolate);

      MonupleChartValueGroupBean strawberry = new MonupleChartValueGroupBean();
      strawberry.setGroupName("Strawberry");
      IntStream.of(0, 0, 0, 59, 182, 391, 415, 261, 75, 31, 0, 0)
        .forEach(value -> strawberry.getValues().add(new BigDecimal(value)));
      data.getChartValueGroups().add(strawberry);

      getFieldByClass(TopBox.ChartBox.ChartField.class).getChart().setData(data);
    }
  }
}
