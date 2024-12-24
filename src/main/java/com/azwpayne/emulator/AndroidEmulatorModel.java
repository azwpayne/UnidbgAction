package com.azwpayne.emulator;


import com.github.unidbg.arm.backend.BackendFactory;
import java.io.File;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/azwpayne">azpayne</a>
 */
public class AndroidEmulatorModel {

  @Setter
  private boolean cpuInstructionType;

  @Getter
  @Setter
  private String appProcessName;

  @Setter
  @Getter
  private String appRootDir;

  @Getter
  private Collection<BackendFactory> backendFactories;

  @Setter
  private int libResolverSdk;

  @Setter
  @Getter
  private boolean jniDebug;

  @Getter
  private File libElf;

  @Setter
  @Getter
  private boolean activeInit;

  @Getter
  private String targetClassPath;


  boolean isCpuInstructionType() {
    return !cpuInstructionType;
  }


  public int getLibResolverSdk() {
    if (this.libResolverSdk != 18 && this.libResolverSdk != 23) {
      return 23;
    }
    return this.libResolverSdk;
  }

  public void setLibElf(String libElf) {
    File libfile = new File(libElf);
    if (!libfile.exists()) {
      throw new IllegalArgumentException("libElf=" + this.libElf + " not found");
    }
    this.libElf = libfile;
  }

  public void setTargetClassPath(String targetClassPath) {
    if (targetClassPath == null || targetClassPath.isEmpty()) {
      throw new IllegalArgumentException("targetClassPath == null");
    }
    this.targetClassPath = targetClassPath;
  }

  public void setBackendFactories(Collection<BackendFactory> backendFactories) {
    if (backendFactories != null && !backendFactories.isEmpty() && backendFactories.size() < 6) {
      this.backendFactories = backendFactories;
    }

  }

}
