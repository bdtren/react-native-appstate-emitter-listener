#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AppstateEmitterListener, NSObject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
