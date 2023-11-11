export type ListenerEventTypes = 'change' | 'activityChange' | 'window';
export type ChangeStates = 'active' | 'inactive' | 'destroyed';
export type ActivityChangeStates =
  | 'create'
  | 'start'
  | 'pause'
  | 'resume'
  | 'stop'
  | 'destroy';
export type ListenerEventProps = {
  change: ListenerChangeEventProps;
  activityChange: ListenerActivityChangeEventProps;
  window: ListenerWindowEventProps;
};

export type ListenerChangeEventProps = {
  state?: ChangeStates;
};
export type ListenerActivityChangeEventProps = {
  state?: ActivityChangeStates;
};
export type ListenerWindowEventProps = {
  isFocused?: boolean;
};
