package org.example;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Resolver {

    // Méthodo que se ejecuta al iniciar el programa, que mostrará los datos del equipo local, el hostname y la IP.
    private static void mostrarDatosEquipoLocal() {
        try {
            // 1º Pillamos unestro equipo
            InetAddress equipo = InetAddress.getLocalHost();
            // 2º Mostramos el hostaname y la IP
            System.out.println("Nombre del Equipo local: " + equipo.getHostName());
            System.out.println("IP local: " + equipo.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("No se ha podido obtener la información del equipo local: " + e.getMessage());
        }
    }

    // Se devolverán los datos del dominio introducido
    private static void resolverDominio(String dominio) {
        try {
            // Pillamos las ips del dominio
            InetAddress[] direcciones = InetAddress.getAllByName(dominio);
            System.out.println("Direcciones IP de " + dominio + ":");

            // Por cada dirección ip, imprimiremos sus datos
            for (InetAddress direccion : direcciones) {
                String tipo = obtenerTipoDireccion(direccion);
                System.out.println("[" + tipo + "]: " + direccion.getHostAddress());
            }
            // En caso de que el dominio no exista, saltarála excepción UnknownHostException, mostrando un mensaje personalizado.
        } catch (UnknownHostException e) {
            System.out.println("[Error]: el dominio '" + dominio + "' no existe o no se pudo resolver.");
        }
    }

    // Diferenciaremos los tipos de dirección, entre IPv4, IPv6 e IP genérica.
    private static String obtenerTipoDireccion(InetAddress direccion) {
        if (direccion instanceof Inet4Address) {
            return "IPv4";
        }
        if (direccion instanceof Inet6Address) {
            return "IPv6";
        }
        return "IP";
    }

    // Ejecución del main, puse comentario por poner comentario a verdad.
    public static void main(String[] args) {
        // Antes del bucle mostraremos los datos del dispositivo local.
        mostrarDatosEquipoLocal();
        // Metemos scanner, nunca olvidaré que no supe hacerlo en el examen.
        Scanner scanner = new Scanner(System.in);
        // Realizamos el bucle para que el usuarioe scriba el dominio hasta que ponga salir tanto en modo mayúsculas como minúsculas.
        while (true) {
            System.out.print("\nIntroduce un dominio (o escribe 'salir'): ");
            String dominio = scanner.nextLine().trim();

            if (dominio.equalsIgnoreCase("salir")) {
                System.out.println("Programa terminado.");
                break;
            }
            // Si no escribe nada le indicaremos al usuario que no escribió nada y el bucle se reiniciará.
            if (dominio.isEmpty()) {
                System.out.println("Debes introducir un nombre de dominio.");
                continue;
            }

            // Ahora, una vez tenemos el dominio introducido por el usuario, sacaremos sus datos.
            resolverDominio(dominio);
        }
    }

}
