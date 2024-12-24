package com.azwpayne.emulator;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.arm.backend.BackendFactory;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.jni.ProxyClassFactory;
import com.github.unidbg.memory.Memory;

/**
 * @author <a href="https://github.com/azwpayne">azpayne</a>
 */
public class AndroidEmulatorBase {

  protected final VM vm;
  protected final DvmClass dvmClass;
  protected final Memory memory;
  protected final AndroidEmulator emulator;
  protected final DalvikModule dm;

  public AndroidEmulatorBase(final AndroidEmulatorModel args) {

    AndroidEmulatorBuilder aeb = args.isCPUInstructionType() ? AndroidEmulatorBuilder.for32Bit()
        : AndroidEmulatorBuilder.for64Bit();

    if (args.getAppProcessName() != null && !args.getAppProcessName().isEmpty()) {
      aeb.setProcessName(args.getAppProcessName());
    }

    if (args.getAppRootDir() != null && !args.getAppRootDir().isEmpty()) {
      aeb.setProcessName(args.getAppRootDir());
    }

    if (args.getBackendFactories() != null && !args.getBackendFactories().isEmpty()) {
      for (BackendFactory backendFactory : args.getBackendFactories()) {
        aeb.addBackendFactory(backendFactory);
      }
    }

    emulator = aeb.build();

    memory = emulator.getMemory();
    memory.setLibraryResolver(new AndroidResolver(args.getLibResolverSDK()));

    vm = emulator.createDalvikVM();
    vm.setDvmClassFactory(new ProxyClassFactory());

    vm.setVerbose(args.isJNIDebug());

    dvmClass = vm.resolveClass(args.getTargetClassPath());

    dm = vm.loadLibrary(args.getLibELF(), args.isActiveInit());
    dm.callJNI_OnLoad(emulator);
  }

}
