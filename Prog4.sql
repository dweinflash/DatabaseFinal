create table Patient (
	PID number,
	lName varchar2(30) not null,
	fName varchar2(30) not null,
	gender varchar2(1), 
	DOB date,
	address varchar2(30),
	contactNo varchar2(10),
	constraint PT_Gender check (gender in ('M' , 'F')),
	constraint PK_Patient primary key(PID)
	);
	
create table Building (
	bldgName varchar2(30),
	address varchar2(30),
	constraint PK_Building primary key(bldgName)
);

create table Room (
	roomNo number,
	bldgName varchar2(30) not null,
	roomType varchar2(30),
	capacty number,
	constraint PK_Room primary key(roomNo),
	constraint FK_RoomInBuilding foreign key(bldgName) references Building(bldgName)
);

create table Department (
	deptID number,
	name varchar2(30),
	bldgName varchar2(30),
	deptOffNum number,
	constraint PK_Dept primary key(deptID),
	constraint FK_DepartmentHQBuilding foreign key(bldgName) references Building(bldgName)
);

create table Doctor (
	DID number,
	lName varchar2(30) not null,
	fName varchar2(30) not null,
	DOB date not null,
	deptID number,
	officeNo number,
	status varchar2(15) not null,
	constraint PK_Doctor primary key(DID),
	constraint FK_DoctorDept foreign key (deptID) references Department(deptID),
	constraint FK_DoctorOffice foreign key (officeNo) references Room(roomNo)
);


create table Pharmacist (
	pharmID number,
	lName varchar2(30) not null,
	fName varchar2(30) not null,
	DOB date not null,
	deptID number,
	officeNo number,
	constraint PK_Pharm primary key(pharmID),
	constraint FK_PharmDept foreign key (deptID) references Department(deptID),
	constraint FK_PharmOffice foreign key (officeNo) references Room(roomNo)
);
create table PrescriptionRecord (
	prescriptionID number,
	pharmID number not null,
	PID number not null,
	medName varchar2(30),
	prescriptDays number,
	constraint PK_ScriptRecord primary key(prescriptionID),
	constraint FK_ScriptPatient foreign key(PID) references Patient(PID),
	constraint FK_ScriptPharm foreign key(pharmId) references Pharmacist(pharmId)
);

create table Nurse (
	nurseID number,
	lName varchar2(30) not null,
	fName varchar2(30) not null,
	DOB date not null,
	deptID number,
	roomNo number,
	constraint PK_Nurse primary key(nurseID),
	constraint FK_NurseRoom foreign key(roomNo) references Room(roomNo)
);

create table StaffType (
	title varchar2(30),
	officeNo number,
	constraint PK_StaffType primary key(title),
	constraint FK_StaffOffice foreign key(officeNo) references Room(roomNo)
);

create table Staff (
	EID number,
	lName varchar2(30) not null,
	fName varchar2(30) not null,
	DOB date not null,
	deptID number,
	salary number,
	contactNo varchar2(10),
	title varchar2(30),
	gender varchar2(1),
	constraint Staff_Gender check (gender in ('M' , 'F')),
	constraint PK_Staff primary key(EID),
	constraint FK_StaffDept foreign key (deptID) references Department(deptID),
	constraint FK_StaffTitle foreign key (title) references StaffType(title)
);

create table Appointment (
	PID number not null,
	apptNumber number,
	apptDate date,
	receptionistEID number,
	constraint PK_Appt primary key (PID, apptNumber),
	constraint FK_ApptPatient foreign key(PID) references Patient(PID),
	constraint FK_ReceptionistAppt foreign key (receptionistEID) references Staff(EID)
);



create table TreatmentRecord (
	PID number not null,
	DID number not null,
	apptNo number not null, 
	visitReason varchar2(30),
	visitDate date not null,
	dateHospitalized date,
	expectedDischarge date,
	actualDischarge date,
	roomNo number,
	treatmentMethod varchar2(30),
	constraint PK_treatmentRecord primary key (PID, DID, apptNo),
	constraint FK_treatmentRoom foreign key(roomNo) references Room(roomNo),
	constraint FK_TreatmentPatient foreign key(PID) references Patient(PID),
	constraint FK_TreatmentDoctor foreign key(DID) references Doctor(DID),	
	constraint FK_apptNum foreign key(PID, apptNo) references Appointment(PID, apptNumber)
);


create table PaymentRecord (
	paymentID number,
	cashierEID number not null,
	PID number not null,
	amountDue number not null,
	dueDate date,
	paymentStatus varchar2(15),
	paymentDate date,
	constraint PK_Payment primary key(paymentID),
	constraint FK_BillPatient foreign key(PID) references Patient(PID),
	constraint FK_CashierPayment foreign key (cashierEID) references Staff(EID)
);
