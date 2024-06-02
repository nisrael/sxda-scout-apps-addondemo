package io.sxda.scout.apps.addondemo.client.ace;

import io.sxda.scout.apps.addondemo.shared.ace.AceFormData;
import io.sxda.scout.apps.addondemo.shared.ace.IAceService;
import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Contains Tests for the {@link AceForm}.
 *
 * @author nisrael
 */
@RunWith(ClientTestRunner.class)
@RunWithSubject("anonymous")
@RunWithClientSession(TestEnvironmentClientSession.class)
public class AceFormTest {

  public static final String HELLO_WORLD = "Hello World";
  @BeanMock
  private IAceService m_mockSvc;

  @Before
  public void setup() {
    AceFormData result = new AceFormData();
    result.getAce().setValue(HELLO_WORLD);
    when(m_mockSvc.load(any())).thenReturn(result);
  }

  /**
   * Tests that the {@link AceForm.MainBox.TopBox.AceField} is enabled.
   */
  @Test
  public void testAceFieldIsEnabled() {
    AceForm frm = new AceForm();
    assertTrue(frm.getAceField().isEnabled());
  }

  /**
   * Tests that the {@link AceForm.MainBox.TopBox.ContentsField} is correctly filled after start.
   */
  @Test
  public void testContentFieldShowAceFieldContents() {
    AceForm frm = new AceForm();
    frm.start();
    assertNotNull(frm.getContentsField().getValue());
    assertEquals(HELLO_WORLD, frm.getContentsField().getValue());
  }
}
