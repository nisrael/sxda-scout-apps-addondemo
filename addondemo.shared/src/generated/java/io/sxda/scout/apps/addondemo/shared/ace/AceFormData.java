package io.sxda.scout.apps.addondemo.shared.ace;

import jakarta.annotation.Generated;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "io.sxda.scout.apps.addondemo.client.ace.AceForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class AceFormData extends AbstractFormData {
    private static final long serialVersionUID = 1L;

    public Ace getAce() {
        return getFieldByClass(Ace.class);
    }

    public Contents getContents() {
        return getFieldByClass(Contents.class);
    }

    public static class Ace extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    public static class Contents extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }
}
