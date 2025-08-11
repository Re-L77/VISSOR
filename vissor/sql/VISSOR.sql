-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 10-08-2025 a las 04:53:17
-- Versión del servidor: 8.0.42
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `VISSOR`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATOS_PLCS`
--

CREATE TABLE `DATOS_PLCS` (
  `id_dato` int NOT NULL,
  `id_plc` int NOT NULL,
  `timestamp` datetime NOT NULL,
  `tag` varchar(50) NOT NULL,
  `valor` float NOT NULL
) ;

--
-- Volcado de datos para la tabla `DATOS_PLCS`
--

INSERT INTO `DATOS_PLCS` (`id_dato`, `id_plc`, `timestamp`, `tag`, `valor`) VALUES
(1, 1, '2025-08-04 08:00:00', 'Temperatura', 72.5),
(2, 1, '2025-08-04 08:05:00', 'Presion', 1.2),
(3, 2, '2025-08-04 08:00:00', 'Flujo', 5.8),
(4, 3, '2025-08-04 08:10:00', 'Temperatura', 65.3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MANTENIMIENTOS`
--

CREATE TABLE `MANTENIMIENTOS` (
  `id_mantenimiento` int NOT NULL,
  `id_plc` int NOT NULL,
  `tipo` enum('Correctivo','Preventivo','Predictivo') NOT NULL,
  `fecha_programada` date NOT NULL,
  `fecha_realizada` date DEFAULT NULL,
  `descripcion` text
) ;

--
-- Volcado de datos para la tabla `MANTENIMIENTOS`
--

INSERT INTO `MANTENIMIENTOS` (`id_mantenimiento`, `id_plc`, `tipo`, `fecha_programada`, `fecha_realizada`, `descripcion`) VALUES
(1, 1, 'Preventivo', '2025-08-01', '2025-08-01', 'Cambio de filtro y revisión general.'),
(2, 2, 'Correctivo', '2025-08-03', '2025-08-04', 'Reemplazo de sensor de temperatura.'),
(3, 3, 'Predictivo', '2025-08-10', '2025-08-05', 'Análisis de vibraciones programado.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERMISOS`
--

CREATE TABLE `PERMISOS` (
  `id_permiso` int NOT NULL,
  `id_rol` int NOT NULL,
  `recurso` varchar(100) NOT NULL
);

--
-- Volcado de datos para la tabla `PERMISOS`
--

INSERT INTO `PERMISOS` (`id_permiso`, `id_rol`, `recurso`) VALUES
(1, 1, 'editar_usuarios'),
(2, 1, 'ver_datos'),
(3, 2, 'ver_datos'),
(4, 3, 'ver_reportes'),
(5, 2, 'editar_datos'),
(6, 3, 'ver_datos'),
(7, 4, 'gestionar_mantenimientos'),
(8, 5, 'analizar_datos'),
(9, 6, 'ver_operaciones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PLCS`
--

CREATE TABLE `PLCS` (
  `id_plc` int NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `ip` varchar(15) NOT NULL,
  `protocolo` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1'
);

--
-- Volcado de datos para la tabla `PLCS`
--

INSERT INTO `PLCS` (`id_plc`, `modelo`, `ubicacion`, `ip`, `protocolo`, `activo`) VALUES
(1, 'Siemens S7-1200', 'Línea 1 - Empaque', '192.168.0.10', 'Modbus', 1),
(2, 'Allen-Bradley Micro820', 'Línea 2 - Ensamble', '192.168.0.11', 'MQTT', 1),
(3, 'Omron CP1E', 'Línea 3 - Pintura', '192.168.0.12', 'Modbus', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `REPORTES_PRODUCCION`
--

CREATE TABLE `REPORTES_PRODUCCION` (
  `id_reporte` int NOT NULL,
  `fecha` date NOT NULL,
  `eficiencia` float NOT NULL,
  `scrap` float NOT NULL,
  `generado_por` int NOT NULL
);

--
-- Volcado de datos para la tabla `REPORTES_PRODUCCION`
--

INSERT INTO `REPORTES_PRODUCCION` (`id_reporte`, `fecha`, `eficiencia`, `scrap`, `generado_por`) VALUES
(1, '2025-08-03', 89.5, 3.2, 1),
(2, '2025-08-04', 92.1, 2.5, 3),
(3, '2025-08-04', 85.7, 4.8, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ROLES`
--

CREATE TABLE `ROLES` (
  `id_rol` int NOT NULL,
  `nombre` varchar(50) NOT NULL
);

--
-- Volcado de datos para la tabla `ROLES`
--

INSERT INTO `ROLES` (`id_rol`, `nombre`) VALUES
(1, 'administrador'),
(5, 'analista_datos'),
(4, 'gerente_mantenimiento'),
(3, 'gerente_produccion'),
(6, 'operador'),
(2, 'tecnico_plc');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIOS`
--

CREATE TABLE `USUARIOS` (
  `id_usuario` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `id_rol` int NOT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `creado_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Volcado de datos para la tabla `USUARIOS`
--

INSERT INTO `USUARIOS` (`id_usuario`, `nombre`, `correo`, `contrasena`, `id_rol`, `activo`, `creado_en`) VALUES
(1, 'Juan Pérez', 'juan.perez@empresa.com', 'hashed_password_1', 1, 1, '2025-08-09 02:55:47'),
(2, 'Ana García', 'ana.garcia@empresa.com', 'hashed_password_2', 2, 1, '2025-08-09 02:55:47'),
(3, 'Carlos López', 'carlos.lopez@empresa.com', 'hashed_password_3', 3, 1, '2025-08-09 02:55:47'),
(4, 'Sofía Rodríguez', 'sofia.rodriguez@empresa.com', 'hashed_password_4', 4, 1, '2025-08-09 02:55:47'),
(5, 'Pedro Gómez', 'pedro.gomez@empresa.com', 'hashed_password_5', 5, 1, '2025-08-09 02:55:47'),
(6, 'Laura Torres', 'laura.torres@empresa.com', 'hashed_password_6', 6, 1, '2025-08-09 02:55:47');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `DATOS_PLCS`
--
ALTER TABLE `DATOS_PLCS`
  ADD PRIMARY KEY (`id_dato`),
  ADD KEY `id_plc` (`id_plc`);

--
-- Indices de la tabla `MANTENIMIENTOS`
--
ALTER TABLE `MANTENIMIENTOS`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `id_plc` (`id_plc`);

--
-- Indices de la tabla `PERMISOS`
--
ALTER TABLE `PERMISOS`
  ADD PRIMARY KEY (`id_permiso`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `PLCS`
--
ALTER TABLE `PLCS`
  ADD PRIMARY KEY (`id_plc`);

--
-- Indices de la tabla `REPORTES_PRODUCCION`
--
ALTER TABLE `REPORTES_PRODUCCION`
  ADD PRIMARY KEY (`id_reporte`),
  ADD KEY `generado_por` (`generado_por`);

--
-- Indices de la tabla `ROLES`
--
ALTER TABLE `ROLES`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `USUARIOS`
--
ALTER TABLE `USUARIOS`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `id_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `DATOS_PLCS`
--
ALTER TABLE `DATOS_PLCS`
  MODIFY `id_dato` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `MANTENIMIENTOS`
--
ALTER TABLE `MANTENIMIENTOS`
  MODIFY `id_mantenimiento` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `PERMISOS`
--
ALTER TABLE `PERMISOS`
  MODIFY `id_permiso` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `PLCS`
--
ALTER TABLE `PLCS`
  MODIFY `id_plc` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `REPORTES_PRODUCCION`
--
ALTER TABLE `REPORTES_PRODUCCION`
  MODIFY `id_reporte` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ROLES`
--
ALTER TABLE `ROLES`
  MODIFY `id_rol` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `USUARIOS`
--
ALTER TABLE `USUARIOS`
  MODIFY `id_usuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `DATOS_PLCS`
--
ALTER TABLE `DATOS_PLCS`
  ADD CONSTRAINT `DATOS_PLCS_ibfk_1` FOREIGN KEY (`id_plc`) REFERENCES `PLCS` (`id_plc`);

--
-- Filtros para la tabla `MANTENIMIENTOS`
--
ALTER TABLE `MANTENIMIENTOS`
  ADD CONSTRAINT `MANTENIMIENTOS_ibfk_1` FOREIGN KEY (`id_plc`) REFERENCES `PLCS` (`id_plc`);

--
-- Filtros para la tabla `PERMISOS`
--
ALTER TABLE `PERMISOS`
  ADD CONSTRAINT `PERMISOS_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `ROLES` (`id_rol`);

--
-- Filtros para la tabla `REPORTES_PRODUCCION`
--
ALTER TABLE `REPORTES_PRODUCCION`
  ADD CONSTRAINT `REPORTES_PRODUCCION_ibfk_1` FOREIGN KEY (`generado_por`) REFERENCES `USUARIOS` (`id_usuario`);

--
-- Filtros para la tabla `USUARIOS`
--
ALTER TABLE `USUARIOS`
  ADD CONSTRAINT `USUARIOS_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `ROLES` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
