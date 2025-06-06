
from abc import ABC, abstractmethod

class Vehiculo(ABC):  # Clase abstracta
    @abstractmethod
    def mover(self):
        pass

class Auto(Vehiculo):
    def mover(self):
        return "El auto avanza por la carretera."

# Uso
mi_auto = Auto()
print(mi_auto.mover())  # Output: "El auto avanza por la carretera."
# encapsulacion/ejemplo_encapsulacion.py
class CuentaBancaria:
    def __init__(self):
        self.__saldo = 0  # Atributo privado

    def depositar(self, monto):
        if monto > 0:
            self.__saldo += monto

    def get_saldo(self):  # Getter
        return f"Saldo actual: ${self.__saldo}"

# Uso
cuenta = CuentaBancaria()
cuenta.depositar(100)
print(cuenta.get_saldo())  # Output: "Saldo actual: $100"
print(cuenta.__saldo)      # Error: Atributo privado
# herencia/ejemplo_herencia.py
class Animal:
    def __init__(self, nombre):
        self.nombre = nombre

    def hablar(self):
        raise NotImplementedError("Método no implementado")

class Perro(Animal):
    def hablar(self):
        return f"{self.nombre} dice: ¡Guau!"

# Uso
mi_perro = Perro("Fido")
print(mi_perro.hablar())  # Output: "Fido dice: ¡Guau!"
