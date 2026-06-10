---

## 📋 RESUMEN DE ENDPOINTS Y ESQUEMA DE PRUEBAS

### **Base URL:** `http://localhost:8081`

---

## 1️⃣ **PERSONAS** (`/api/personas`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/personas` | Crear una nueva persona |
| GET | `/api/personas` | Obtener todas las personas |
| GET | `/api/personas/{id}` | Obtener persona por ID |
| GET | `/api/personas/dni/{dni}` | Obtener persona por DNI |
| GET | `/api/personas/email/{email}` | Obtener persona por email |
| PUT | `/api/personas/{id}` | Actualizar persona |
| DELETE | `/api/personas/{id}` | Eliminar persona |

---

## 2️⃣ **INMUEBLES** (`/api/inmuebles`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/inmuebles` | Crear nuevo inmueble |
| GET | `/api/inmuebles` | Obtener todos los inmuebles |
| GET | `/api/inmuebles/{id}` | Obtener inmueble por ID |
| GET | `/api/inmuebles/buscar/direccion?q=...` | Buscar por dirección |
| GET | `/api/inmuebles/buscar/tipo?tipo=...` | Buscar por tipo |
| PUT | `/api/inmuebles/{id}` | Actualizar inmueble |
| DELETE | `/api/inmuebles/{id}` | Eliminar inmueble |

---

## 3️⃣ **CONTRATOS** (`/api/contratos`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/contratos` | Crear nuevo contrato |
| GET | `/api/contratos` | Obtener todos los contratos |
| GET | `/api/contratos/{id}` | Obtener contrato por ID |
| GET | `/api/contratos/inmueble/{inmuebleId}` | Obtener contratos de un inmueble |

---

## 4️⃣ **PAGOS** (`/api/pagos`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/pagos` | Registrar un pago a una cuota |
| GET | `/api/pagos/cuota/{cuotaId}` | Obtener pagos de una cuota |

---

## 🧪 ESQUEMA COMPLETO DE PRUEBAS CON DATOS

### **PASO 1: Crear Personas**

#### 1.1 Crear Propietario
```bash
POST http://localhost:8081/api/personas
Content-Type: application/json

{
  "nombreCompleto": "Juan García López",
  "dni": "12345678",
  "email": "juan.garcia@example.com",
  "telefono": "1123456789"
}
```
**Respuesta esperada:** `201 CREATED`
```json
{
  "id": 1,
  "nombreCompleto": "Juan García López",
  "dni": "12345678",
  "email": "juan.garcia@example.com",
  "telefono": "1123456789"
}
```

#### 1.2 Crear Inquilino
```bash
POST http://localhost:8081/api/personas
Content-Type: application/json

{
  "nombreCompleto": "María Rodríguez Pérez",
  "dni": "87654321",
  "email": "maria.rodriguez@example.com",
  "telefono": "1134567890"
}
```
**Respuesta esperada:** `201 CREATED` with `id: 2`

#### 1.3 Crear Garante
```bash
POST http://localhost:8081/api/personas
Content-Type: application/json

{
  "nombreCompleto": "Carlos Fernández Díaz",
  "dni": "11223344",
  "email": "carlos.fernandez@example.com",
  "telefono": "1145678901"
}
```
**Respuesta esperada:** `201 CREATED` with `id: 3`

#### 1.4 Obtener todas las personas
```bash
GET http://localhost:8081/api/personas
```
**Respuesta esperada:** `200 OK` con array de 3 personas

#### 1.5 Obtener persona por ID
```bash
GET http://localhost:8081/api/personas/1
```
**Respuesta esperada:** `200 OK` con datos del propietario

#### 1.6 Obtener persona por DNI
```bash
GET http://localhost:8081/api/personas/dni/12345678
```
**Respuesta esperada:** `200 OK` con datos del propietario

#### 1.7 Obtener persona por Email
```bash
GET http://localhost:8081/api/personas/email/maria.rodriguez@example.com
```
**Respuesta esperada:** `200 OK` con datos del inquilino

#### 1.8 Actualizar Persona
```bash
PUT http://localhost:8081/api/personas/1
Content-Type: application/json

{
  "nombreCompleto": "Juan García López",
  "dni": "12345678",
  "email": "juan.garcia.nuevo@example.com",
  "telefono": "1198765432"
}
```
**Respuesta esperada:** `200 OK` con datos actualizados

---

### **PASO 2: Crear Inmuebles**

#### 2.1 Crear Inmueble 1 (Departamento)
```bash
POST http://localhost:8081/api/inmuebles
Content-Type: application/json

{
  "direccion": "Av. Corrientes 1000, CABA",
  "tipo": "departamento",
  "precioTasado": 150000.00
}
```
**Respuesta esperada:** `201 CREATED`
```json
{
  "id": 1,
  "direccion": "Av. Corrientes 1000, CABA",
  "tipo": "departamento",
  "precioTasado": 150000.00,
  "estado": "DISPONIBLE"
}
```

#### 2.2 Crear Inmueble 2 (Casa)
```bash
POST http://localhost:8081/api/inmuebles
Content-Type: application/json

{
  "direccion": "Calle Belgrano 500, La Plata",
  "tipo": "casa",
  "precioTasado": 250000.00
}
```
**Respuesta esperada:** `201 CREATED` with `id: 2`

#### 2.3 Obtener todos los inmuebles
```bash
GET http://localhost:8081/api/inmuebles
```
**Respuesta esperada:** `200 OK` con array de 2 inmuebles

#### 2.4 Obtener inmueble por ID
```bash
GET http://localhost:8081/api/inmuebles/1
```
**Respuesta esperada:** `200 OK` con datos del departamento

#### 2.5 Buscar inmuebles por dirección
```bash
GET http://localhost:8081/api/inmuebles/buscar/direccion?q=Corrientes
```
**Respuesta esperada:** `200 OK` con el departamento de Av. Corrientes

#### 2.6 Buscar inmuebles por tipo
```bash
GET http://localhost:8081/api/inmuebles/buscar/tipo?tipo=casa
```
**Respuesta esperada:** `200 OK` con la casa de La Plata

#### 2.7 Actualizar Inmueble
```bash
PUT http://localhost:8081/api/inmuebles/1
Content-Type: application/json

{
  "direccion": "Av. Corrientes 1000, CABA",
  "tipo": "departamento de lujo",
  "precioTasado": 180000.00
}
```
**Respuesta esperada:** `200 OK` con datos actualizados

---

### **PASO 3: Crear Contratos**

#### 3.1 Crear Contrato CON Garante
```bash
POST http://localhost:8081/api/contratos
Content-Type: application/json

{
  "inmuebleId": 1,
  "propietarioId": 1,
  "inquilinoId": 2,
  "garanteId": 3,
  "fechaInicio": "2024-01-01",
  "fechaFin": "2026-01-01",
  "montoBase": 15000.00
}
```
**Respuesta esperada:** `201 CREATED`
```json
{
  "id": 1,
  "inmueble": { "id": 1, ... },
  "propietario": { "id": 1, ... },
  "inquilino": { "id": 2, ... },
  "garante": { "id": 3, ... },
  "fechaInicio": "2024-01-01",
  "fechaFin": "2026-01-01",
  "montoBase": 15000.00,
  "cuotas": [...]
}
```

#### 3.2 Crear Contrato SIN Garante
```bash
POST http://localhost:8081/api/contratos
Content-Type: application/json

{
  "inmuebleId": 2,
  "propietarioId": 1,
  "inquilinoId": 2,
  "garanteId": null,
  "fechaInicio": "2024-06-01",
  "fechaFin": "2025-06-01",
  "montoBase": 20000.00
}
```
**Respuesta esperada:** `201 CREATED` with `id: 2`

#### 3.3 Obtener todos los contratos
```bash
GET http://localhost:8081/api/contratos
```
**Respuesta esperada:** `200 OK` con array de 2 contratos

#### 3.4 Obtener contrato por ID
```bash
GET http://localhost:8081/api/contratos/1
```
**Respuesta esperada:** `200 OK` con datos del contrato

#### 3.5 Obtener contratos por inmueble
```bash
GET http://localhost:8081/api/contratos/inmueble/1
```
**Respuesta esperada:** `200 OK` con 1 contrato (el del inmueble 1)

#### 3.6 Obtener contratos por otro inmueble
```bash
GET http://localhost:8081/api/contratos/inmueble/2
```
**Respuesta esperada:** `200 OK` con 1 contrato (el del inmueble 2)

---

### **PASO 4: Registrar Pagos a Cuotas**

> **Nota:** Los contratos crean automáticamente cuotas mensuales. Para obtener el ID de una cuota, primero obtén el contrato y revisa su lista de cuotas.

#### 4.1 Obtener cuotas de un contrato
```bash
GET http://localhost:8081/api/contratos/1
```
**Extrae el ID de la primera cuota de la respuesta** (ej: `cuotaId = 1`)

#### 4.2 Registrar Pago Parcial a una Cuota
```bash
POST http://localhost:8081/api/pagos
Content-Type: application/json

{
  "cuotaId": 1,
  "monto": 7500.00,
  "metodoPago": "transferencia",
  "nroComprobante": "TRANSFER-001"
}
```
**Respuesta esperada:** `201 CREATED`
```json
{
  "id": 1,
  "monto": 7500.00,
  "metodoPago": "transferencia",
  "nroComprobante": "TRANSFER-001",
  "fechaPago": "2024-12-10",
  "cuota": { "id": 1, "estado": "PENDIENTE", ... }
}
```

#### 4.3 Registrar Segundo Pago para Completar la Cuota
```bash
POST http://localhost:8081/api/pagos
Content-Type: application/json

{
  "cuotaId": 1,
  "monto": 7500.00,
  "metodoPago": "debito",
  "nroComprobante": "DEBITO-001"
}
```
**Respuesta esperada:** `201 CREATED`
```json
{
  "id": 2,
  "monto": 7500.00,
  "metodoPago": "debito",
  "nroComprobante": "DEBITO-001",
  "fechaPago": "2024-12-10",
  "cuota": { "id": 1, "estado": "PAGADA", ... }
}
```

> **Importante:** El estado de la cuota cambia a `PAGADA` cuando el total de pagos iguala al monto de la cuota.

#### 4.4 Obtener todos los pagos de una cuota
```bash
GET http://localhost:8081/api/pagos/cuota/1
```
**Respuesta esperada:** `200 OK` con array de 2 pagos

---

## 🧪 ORDEN RECOMENDADO PARA PROBAR

1. ✅ Crear 3 personas (paso 1.1-1.3)
2. ✅ Validar personas creadas (paso 1.4-1.7)
3. ✅ Crear 2 inmuebles (paso 2.1-2.2)
4. ✅ Validar inmuebles (paso 2.4-2.6)
5. ✅ Crear 2 contratos (paso 3.1-3.2)
6. ✅ Validar contratos (paso 3.3-3.6)
7. ✅ Registrar pagos a cuotas (paso 4.2-4.4)
8. ✅ Pruebas de actualización (paso 1.8, 2.7)
9. ✅ Pruebas de eliminación (DELETE endpoints)

---
## MER

<img width="729" height="455" alt="image" src="https://github.com/user-attachments/assets/5c019435-f40d-4862-87a6-41dd0ea2fd05" />
