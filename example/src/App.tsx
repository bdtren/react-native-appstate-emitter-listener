import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import {
  addEventListener,
  initActivityListener,
  type ChangeStates,
} from 'react-native-appstate-emitter-listener';

export default function App() {
  const [state, setState] = React.useState<ChangeStates | undefined>('active');

  React.useEffect(() => {
    initActivityListener();

    addEventListener('change', (e) => {
      console.log('state changed:', e);
      setState(e.state);
    });
  }, []);

  return (
    <View style={styles.container}>
      <Text>Current State: {state}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
