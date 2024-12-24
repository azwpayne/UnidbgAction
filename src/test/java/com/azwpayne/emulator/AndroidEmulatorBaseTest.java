package com.azwpayne.emulator;

import com.github.unidbg.arm.backend.KvmFactory;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.jni.ProxyDvmObject;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class AndroidEmulatorBaseTest {

  private AndroidEmulatorBase aeb;

  @BeforeEach
  void setUp() {
    AndroidEmulatorModel aem = new AndroidEmulatorModel();
    aem.setAppProcessName("com.anjuke.android.app");
    aem.setCPUInstructionType(false);
    aem.setLibELF("src/test/resources/lib/armeabi-v7a/libsignutil.so");
    aem.setTargetClassPath("com/anjuke/mobile/sign/SignUtil");

    aem.setBackendFactories(
        Arrays.asList(new KvmFactory(true), new Unicorn2Factory(true)));

    aeb = new AndroidEmulatorBase(aem);
  }

  @Test
  void testAndroidEmulator() {

    Map<String, String> paramMap = new HashMap<String, String>() {{
      put("a", "b");
      put("b", "b");
    }};
    String p1 = "aa";
    String p2 = "bb";
    String p3 = "cc";
    int i = 10;

    AndroidEmulatorBaseTest emulatorBaseTest = new AndroidEmulatorBaseTest();
    Map<String, Object> byteCode = emulatorBaseTest.mapByteCode(paramMap);
    String sign0 = getSign0(p1, p2, byteCode, p3, i);
    log.info("sign0: {}", sign0);
  }

  public String getSign0(String p1, String p2, Map<String, Object> map, String p3, int i) {
    String methodSign = "getSign0(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)Ljava/lang/String;";
    StringObject obj = aeb.dvmClass.callStaticJniMethodObject(aeb.emulator, methodSign, p1, p2,
        ProxyDvmObject.createObject(aeb.vm, map), p3, i);
    return obj.getValue();
  }

  private synchronized Map<String, Object> mapByteCode(Map<String, String> paramMap) {
    Map<String, Object> map = new HashMap<>();
    paramMap.forEach((k, v) -> map.put(k, v.getBytes(StandardCharsets.UTF_8)));
    return map;
  }

  @AfterEach
  void tearDown() {
  }
}