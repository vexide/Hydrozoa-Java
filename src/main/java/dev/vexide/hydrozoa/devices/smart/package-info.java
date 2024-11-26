/**
 * Smart Ports &amp; Devices
 * <p>
 * This module provides abstractions over device access connected through VEX V5 Smart Ports. This includes motors, many
 * common sensors, VEXLink, and raw serial access.
 *
 * <h2>Hardware Overview</h2>
 * <p>
 * The V5 brain features 21 RJ9 4p4c connector ports (known as “Smart Ports”) for communicating with newer V5
 * peripherals. Smart port devices have a variable sample rate (unlike ADI, which is limited to 10ms), and can support
 * basic data transfer over serial.
 *
 * @since 0.1.0
 */
package dev.vexide.hydrozoa.devices.smart;
