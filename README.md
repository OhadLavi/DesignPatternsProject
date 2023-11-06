# Description

The primary objective of this application is to showcase the practical implementation of various design patterns within the application. These design patterns are incorporated to streamline sensor functionality.

## Implemented Design Patterns

### Factory

The Factory design pattern is implemented in the `SensorFactory` class. It is responsible for creating different types of sensors based on requirements and configurations.

### Command

The Command design pattern is utilized in the following classes:
- `CompassSensorCommand`
- `LightSensorCommand`
- `PressureSensorCommand`
- `ProximitySensorCommand`
- `PositionSensorCommand`

Additionally, an interface, `Command`, is employed in the `MainActivity` class. This pattern is designed for executing the `registerSensor` and `unRegisterSensor` methods for each sensor's listener, enhancing the decoupling and flexibility of sensor operations.

### Singleton

The Singleton design pattern is employed in the `LocationManagerSingleton` class. This pattern ensures the existence of a single instance of the `LocationManager` object, primarily used in the `PositionSensorCommand`.

## Preview
![Screenshot](https://user-images.githubusercontent.com/81176492/280681567-01541350-2c7f-4b65-99cc-381699ef3baf.png)

## Class diagram
![Screenshot](https://user-images.githubusercontent.com/81176492/280681585-8acc6401-269c-4246-9cfc-51439d95ab70.png)
