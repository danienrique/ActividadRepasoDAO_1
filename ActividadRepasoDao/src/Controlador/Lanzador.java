package Controlador;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;
import DAO.DAOProducto;
import DAO.DaoProductoImplementadoMYSQL;
import Vista.IVista;
import Vista.VistaConsola;

public class Lanzador {
    public static void main(String[] args) {
        configurarLogs();
        
        DAOProducto dao = new DaoProductoImplementadoMYSQL();
        IVista vista = new VistaConsola();
        Controlador controler = new Controlador(dao, vista);
        controler.iniciar();
    }
    
    private static void configurarLogs() {
        LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        // Silenciar librerías externas
        ctx.getLogger("com.zaxxer.hikari").setLevel(Level.OFF);
        ctx.getLogger("com.mysql").setLevel(Level.OFF);
        
        // Crear archivo de log
        FileAppender fa = new FileAppender();
        fa.setContext(ctx);
        fa.setFile("Resources/app.log");
        
        PatternLayoutEncoder enc = new PatternLayoutEncoder();
        enc.setContext(ctx);
        enc.setPattern("%d{HH:mm:ss} %-5level %logger{20} - %msg%n");
        enc.start();
        
        fa.setEncoder(enc);
        fa.start();
        
        // Añadir al root PRIMERO
        Logger root = ctx.getLogger(Logger.ROOT_LOGGER_NAME);
        root.addAppender(fa);
        
        // Ahora quitar el appender de CONSOLA (no silenciar todo)
        root.detachAppender("console");
    }
}