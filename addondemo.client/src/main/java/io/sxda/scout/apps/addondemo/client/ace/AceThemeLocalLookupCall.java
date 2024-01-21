package io.sxda.scout.apps.addondemo.client.ace;

import io.sxda.scout.addon.ace.client.acefield.AceTheme;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AceThemeLocalLookupCall extends LocalLookupCall<String> {
  @Override
  protected List<? extends ILookupRow<String>> execCreateLookupRows() {
    return Arrays.stream(AceTheme.values()).map(theme -> new LookupRow<>(theme.getConfigTerm(), theme.getConfigTerm())).collect(Collectors.toList());
  }
}
