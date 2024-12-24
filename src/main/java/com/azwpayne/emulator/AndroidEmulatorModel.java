package com.azwpayne.emulator;


import com.github.unidbg.arm.backend.BackendFactory;
import java.io.File;
import java.util.Collection;

/**
 * @author <a href="https://github.com/azwpayne">azpayne</a>
 */
public class AndroidEmulatorModel {

  private boolean CPUInstructionType;
  private String AppProcessName;
  private String AppRootDir;
  private Collection<BackendFactory> backendFactories;
  private int LibResolverSDK;
  private boolean JNIDebug;
  private File LibELF;
  private boolean ActiveInit;
  private String TargetClassPath;


  boolean isCPUInstructionType() {
    return !CPUInstructionType;
  }

  public void setCPUInstructionType(boolean CPUInstructionType) {
    this.CPUInstructionType = CPUInstructionType;
  }

  public String getAppProcessName() {
    return AppProcessName;
  }

  public void setAppProcessName(String appProcessName) {
    this.AppProcessName = appProcessName;
  }


  public String getAppRootDir() {
    return AppRootDir;
  }

  public void setAppRootDir(String appRootDir) {
    this.AppRootDir = appRootDir;
  }


  public int getLibResolverSDK() {
    if (this.LibResolverSDK != 18 && this.LibResolverSDK != 23) {
      return 23;
    }
    return this.LibResolverSDK;
  }

  public void setLibResolverSDK(final int libResolverSDK) {
    this.LibResolverSDK = libResolverSDK;
  }

  public boolean isJNIDebug() {
    return JNIDebug;
  }

  public void setJNIDebug(boolean JNIDebug) {
    this.JNIDebug = JNIDebug;
  }

  public File getLibELF() {
    return LibELF;
  }

  public void setLibELF(String libELF) {
    File libfile = new File(libELF);
    if (!libfile.exists()) {
      throw new IllegalArgumentException("libELF=" + libELF + " not found");
    }
    this.LibELF = libfile;
  }

  public boolean isActiveInit() {
    return ActiveInit;
  }

  public void setActiveInit(boolean activeInit) {
    this.ActiveInit = activeInit;
  }

  public String getTargetClassPath() {
    return TargetClassPath;
  }

  public void setTargetClassPath(String targetClassPath) {
    if (targetClassPath == null || targetClassPath.isEmpty()) {
      throw new IllegalArgumentException("targetClassPath == null");
    }
    this.TargetClassPath = targetClassPath;
  }

  public Collection<BackendFactory> getBackendFactories() {
    return backendFactories;
  }

  public void setBackendFactories(Collection<BackendFactory> backendFactories) {
    if (backendFactories != null && !backendFactories.isEmpty() && backendFactories.size() < 6) {
      this.backendFactories = backendFactories;
    }

  }
}
