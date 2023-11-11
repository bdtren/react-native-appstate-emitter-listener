# react-native-appstate-emitter-listener

Listen to Appstate change

## Installation

```sh
npm install react-native-appstate-emitter-listener
```

## Usage

```js
import {
  addEventListener,
  initActivityListener,
} from 'react-native-appstate-emitter-listener';

// ...

//To init android activity listener
initActivityListener();

//listenable states are: 'change' | 'activityChange' | 'window'
addEventListener('change', (e) => {
  console.log('state changed:', e);
  setState(e.state);
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
