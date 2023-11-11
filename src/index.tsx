import { NativeEventEmitter, NativeModules, Platform } from 'react-native';
import type { ListenerEventProps, ListenerEventTypes } from './index.types';
export * from './index.types';

const isIOS = Platform.OS === 'ios';

const LINKING_ERROR =
  `The package 'react-native-appstate-listener' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const AppstateEmitterListener = NativeModules.AppstateEmitterListener
  ? NativeModules.AppstateEmitterListener
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function initActivityListener() {
  if (isIOS) {
    return undefined;
  }
  return AppstateEmitterListener.initActivityListener();
}

const eventEmitter = new NativeEventEmitter(AppstateEmitterListener);

export const addEventListener = <E extends ListenerEventTypes>(
  eventType: E,
  listener: (event: ListenerEventProps[E]) => any
) => eventEmitter?.addListener(eventType, listener);
