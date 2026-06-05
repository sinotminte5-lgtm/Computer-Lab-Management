
# Computer Lab Management System

A comprehensive Java-based desktop application for managing computer lab resources, user roles, reservations, and maintenance tasks.

## 📋 Overview

The **Computer Lab Management System** is a console-based application designed to efficiently manage computer lab resources in educational institutions. It supports three user roles (Administrator, Lab Assistant, and Student) with role-specific functionalities for resource allocation, reservations, and maintenance scheduling.

## ✨ Features

### 👑 Administrator Functions
- **User Management**: Add, remove, update, search, and display all users
- **Maintenance Scheduling**: Schedule and execute maintenance for computers and peripherals
- **View Reservations**: Monitor all current computer reservations
- **Password Management**: Change personal password

### 🔧 Lab Assistant Functions
- **Resource Allocation**: Assign/release computers and peripherals to users
- **Usage Tracking**: Generate reports on resource usage by users
- **Password Management**: Change personal password

### 🧑‍🎓 Student Functions
- **Computer Reservation**: Reserve available computers for lab sessions
- **Cancel Reservation**: Cancel existing reservations
- **View My Reservations**: See all personal reservations
- **Password Management**: Change personal password

## 🏗️ System Architecture
├── LabManagementSystem.java # Main application entry point
├── User.java # Base user class
├── Administrator.java # Administrator role implementation
├── LabAssistant.java # Lab Assistant role implementation
├── Computer.java # Computer resource entity
├── Peripheral.java # Peripheral resource entity
├── ReservationManager.java # Reservation management logic
├── ReservationSystem.java # Reservation interface
├── MaintenanceTask.java # Abstract maintenance task
├── ComputerMaintenanceTask.java # Computer maintenance implementation
└── PeripheralMaintenanceTask.java # Peripheral maintenance implementation
