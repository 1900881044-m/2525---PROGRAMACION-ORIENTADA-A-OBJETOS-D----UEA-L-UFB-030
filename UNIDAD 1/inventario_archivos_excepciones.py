import os

class Producto:
    def __init__(self, codigo, nombre, cantidad):
        self.codigo = codigo
        self.nombre = nombre
        self.cantidad = cantidad

    def __str__(self):
        return f"{self.codigo},{self.nombre},{self.cantidad}"

class Inventario:
    def __init__(self, archivo="inventario.txt"):
        self.productos = {}
        self.archivo = archivo
        self.cargar_desde_archivo()

    def cargar_desde_archivo(self):
        """Carga los productos desde el archivo al iniciar el programa."""
        try:
            with open(self.archivo, "r") as f:
                for linea in f:
                    partes = linea.strip().split(",")
                    if len(partes) == 3:
                        codigo, nombre, cantidad = partes
                        self.productos[codigo] = Producto(codigo, nombre, int(cantidad))
        except FileNotFoundError:
            print(f"[INFO] El archivo '{self.archivo}' no existe. Se creará automáticamente.")
            self.guardar_en_archivo()
        except PermissionError:
            print("[ERROR] No tienes permisos para leer el archivo.")
        except Exception as e:
            print(f"[ERROR] Ocurrió un problema al cargar el archivo: {e}")

    def guardar_en_archivo(self):
        """Guarda todos los productos en el archivo."""
        try:
            with open(self.archivo, "w") as f:
                for producto in self.productos.values():
                    f.write(str(producto) + "\n")
        except PermissionError:
            print("[ERROR] No tienes permisos para escribir en el archivo.")
        except Exception as e:
            print(f"[ERROR] Ocurrió un problema al guardar el archivo: {e}")

    def agregar_producto(self, codigo, nombre, cantidad):
        if codigo in self.productos:
            print("[ERROR] El producto ya existe.")
            return
        self.productos[codigo] = Producto(codigo, nombre, cantidad)
        self.guardar_en_archivo()
        print("[✔] Producto agregado exitosamente.")

    def actualizar_producto(self, codigo, nueva_cantidad):
        if codigo not in self.productos:
            print("[ERROR] Producto no encontrado.")
            return
        self.productos[codigo].cantidad = nueva_cantidad
        self.guardar_en_archivo()
        print("[✔] Producto actualizado exitosamente.")

    def eliminar_producto(self, codigo):
        if codigo in self.productos:
            del self.productos[codigo]
            self.guardar_en_archivo()
            print("[✔] Producto eliminado exitosamente.")
        else:
            print("[ERROR] Producto no encontrado.")

    def mostrar_inventario(self):
        if not self.productos:
            print("[INFO] El inventario está vacío.")
        else:
            print("\nInventario actual:")
            for producto in self.productos.values():
                print(f"→ Código: {producto.codigo}, Nombre: {producto.nombre}, Cantidad: {producto.cantidad}")

def menu():
    inventario = Inventario()

    while True:
        print("\n--- MENÚ DE INVENTARIO ---")
        print("1. Mostrar inventario")
        print("2. Agregar producto")
        print("3. Actualizar cantidad")
        print("4. Eliminar producto")
        print("5. Salir")

        opcion = input("Selecciona una opción: ")

        if opcion == "1":
            inventario.mostrar_inventario()
        elif opcion == "2":
            codigo = input("Código del producto: ")
            nombre = input("Nombre del producto: ")
            try:
                cantidad = int(input("Cantidad: "))
                inventario.agregar_producto(codigo, nombre, cantidad)
            except ValueError:
                print("[ERROR] La cantidad debe ser un número entero.")
        elif opcion == "3":
            codigo = input("Código del producto a actualizar: ")
            try:
                nueva_cantidad = int(input("Nueva cantidad: "))
                inventario.actualizar_producto(codigo, nueva_cantidad)
            except ValueError:
                print("[ERROR] La cantidad debe ser un número entero.")
        elif opcion == "4":
            codigo = input("Código del producto a eliminar: ")
            inventario.eliminar_producto(codigo)
        elif opcion == "5":
            print("¡Hasta luego!")
            break
        else:
            print("[ERROR] Opción inválida. Intenta de nuevo.")

if __name__ == "__main__":
    menu()
