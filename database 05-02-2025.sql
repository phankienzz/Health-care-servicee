--create database MedicalSystem
SELECT * FROM Staff
select * from role 
update Staff set status = N'Inactive' where staffID = 1;
INSERT [dbo].[Staff] ( [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES ( N'Vu Thang', N'thang@gmail.com', N'thang123', N'1234567890', CONVERT(DATETIME, '15/01/2025', 103), 1, N'Active', N'Administration', NULL)
USE [MedicalSystem]
GO
CREATE TABLE tokenForgetPassword (
    id int IDENTITY(1,1) PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiryTime DATETIME NOT NULL,
	isUsed bit NOT NULL,
	customerID int NOT NULL,
);

/****** Object:  Table [dbo].[Categories]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[description] [text] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comments](
	[comment_id] [int] IDENTITY(1,1) NOT NULL,
	[post_id] [int] NOT NULL,
	[customerID] [int] NOT NULL,
	[content] [text] NOT NULL,
	[status] [bit] NULL,
	[created_at] [datetime] NULL,
	[parent_comment_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[comment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Consultation]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Consultation](
	[consultationID] [int] IDENTITY(1,1) NOT NULL,
	[consultationDate] [datetime] NULL,
	[servicePackageID] [int] NULL,
	[consultationType] [nvarchar](50) NULL,
	[customerID] [int] NULL,
	[status] [nvarchar](20) NULL,
	[consultantID] [int] NULL,
	[notes] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[consultationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customerID] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[fullName] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
	[phone] [nvarchar](15) NULL,
	[address] [nvarchar](255) NULL,
	[accountStatus] [nvarchar](20) NULL,
	[registrationDate] [datetime] NULL,
	[dateOfBirth] [date] NULL,
	[gender] [nvarchar](10) NULL,
	[profilePicture] [varbinary](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[customerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Feedback]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[invoiceID] [int] NULL,
	[rating] [int] NULL,
	[comment] [nvarchar](255) NULL,
	[date] [datetime] NULL,
	[serviceType] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[feedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[invoiceID] [int] IDENTITY(1,1) NOT NULL,
	[customerID] [int] NULL,
	[examinationID] [int] NULL,
	[consultationID] [int] NULL,
	[totalAmount] [decimal](18, 2) NULL,
	[paymentStatus] [nvarchar](20) NULL,
	[paymentDate] [datetime] NULL,
	[paymentMethod] [nvarchar](50) NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[invoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MedicalExamination]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MedicalExamination](
	[examinationID] [int] IDENTITY(1,1) NOT NULL,
	[examinationDate] [datetime] NULL,
	[servicePackageID] [int] NULL,
	[consultationType] [nvarchar](50) NULL,
	[customerID] [int] NULL,
	[status] [nvarchar](20) NULL,
	[diagnosis] [nvarchar](255) NULL,
	[treatmentPlan] [nvarchar](255) NULL,
	[consultantID] [int] NULL,
	[notes] [nvarchar](255) NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[examinationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MedicalRecord]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MedicalRecord](
	[recordID] [int] IDENTITY(1,1) NOT NULL,
	[examinationID] [int] NULL,
	[diagnosis] [nvarchar](255) NULL,
	[treatmentPlan] [nvarchar](255) NULL,
	[medicationsPrescribed] [nvarchar](255) NULL,
	[createdAt] [datetime] NULL,
	[notes] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[recordID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Permission]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Permission](
	[permissionID] [int] IDENTITY(1,1) NOT NULL,
	[permissionName] [nvarchar](50) NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[permissionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Posts]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Posts](
	[post_id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](255) NOT NULL,
	[content] [text] NOT NULL,
	[created_by] [int] NOT NULL,
	[category_id] [int] NOT NULL,
	[status] [bit] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[post_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Professional]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Professional](
	[professionalID] [int] IDENTITY(1,1) NOT NULL,
	[specialization] [nvarchar](100) NOT NULL,
	[gender] [nvarchar](10) NULL,
	[dateOfBirth] [datetime] NULL,
	[address] [nvarchar](255) NULL,
	[officeHours] [nvarchar](100) NULL,
	[qualification] [nvarchar](255) NULL,
	[biography] [nvarchar](max) NULL,
	[profilePicture] [varbinary](max) NULL,
	[status] [nvarchar](20) NULL,
	[createdAt] [datetime] NULL,
	[staffID] [int] NULL,
 CONSTRAINT [PK__Professi__A734593F6505FCC1] PRIMARY KEY CLUSTERED 
(
	[professionalID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RolePermissions]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RolePermissions](
	[roleID] [int] NOT NULL,
	[permissionID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC,
	[permissionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ServicePackage]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServicePackage](
	[packageID] [int] IDENTITY(1,1) NOT NULL,
	[packageName] [nvarchar](100) NOT NULL,
	[description] [nvarchar](255) NULL,
	[type] [nvarchar](255) NULL,
	[price] [decimal](18, 2) NULL,
	[duration] [int] NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[packageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[staffID] [int] IDENTITY(1,1) NOT NULL,
	[fullName] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
	[password] [nvarchar](255) NULL,
	[phone] [nvarchar](15) NULL,
	[hireDate] [datetime] NULL,
	[roleID] [int] NULL,
	[status] [nvarchar](20) NULL,
	[department] [nvarchar](100) NULL,
	[profilePicture] [varbinary](max) NULL,
 CONSTRAINT [PK__Staff__6465E19E19C0644B] PRIMARY KEY CLUSTERED 
(
	[staffID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StaffReplies]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StaffReplies](
	[reply_id] [int] IDENTITY(1,1) NOT NULL,
	[comment_id] [int] NOT NULL,
	[staff_id] [int] NOT NULL,
	[content] [text] NOT NULL,
	[status] [bit] NULL,
	[created_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[reply_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WorkingSchedule]    Script Date: 15/01/2025 9:08:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkingSchedule](
	[scheduleID] [int] IDENTITY(1,1) NOT NULL,
	[professionalID] [int] NULL,
	[workDay] [nvarchar](20) NULL,
	[startTime] [time](7) NULL,
	[endTime] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[scheduleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([category_id], [name], [description], [status]) VALUES (1, N'General Health', N'General health topics', 1)
INSERT [dbo].[Categories] ([category_id], [name], [description], [status]) VALUES (2, N'Cardiology', N'Heart-related articles', 1)
INSERT [dbo].[Categories] ([category_id], [name], [description], [status]) VALUES (3, N'Pediatrics', N'Child health information', 1)
INSERT [dbo].[Categories] ([category_id], [name], [description], [status]) VALUES (4, N'Nutrition', N'Diet and nutrition advice', 1)
INSERT [dbo].[Categories] ([category_id], [name], [description], [status]) VALUES (5, N'Mental Health', N'Mental health resources', 1)
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Comments] ON 

INSERT [dbo].[Comments] ([comment_id], [post_id], [customerID], [content], [status], [created_at], [parent_comment_id]) VALUES (1, 1, 1, N'Very informative article!', 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), NULL)
INSERT [dbo].[Comments] ([comment_id], [post_id], [customerID], [content], [status], [created_at], [parent_comment_id]) VALUES (2, 1, 2, N'Thanks for the tips', 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), NULL)
INSERT [dbo].[Comments] ([comment_id], [post_id], [customerID], [content], [status], [created_at], [parent_comment_id]) VALUES (3, 2, 3, N'This helped me understand vaccinations better', 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), NULL)
INSERT [dbo].[Comments] ([comment_id], [post_id], [customerID], [content], [status], [created_at], [parent_comment_id]) VALUES (4, 3, 4, N'Great dietary advice', 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), NULL)
INSERT [dbo].[Comments] ([comment_id], [post_id], [customerID], [content], [status], [created_at], [parent_comment_id]) VALUES (5, 4, 5, N'Really helpful mental health information', 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Comments] OFF
GO
SET IDENTITY_INSERT [dbo].[Consultation] ON 

INSERT [dbo].[Consultation] ([consultationID], [consultationDate], [servicePackageID], [consultationType], [customerID], [status], [consultantID] , [notes]) VALUES (1, CAST(N'2025-01-16T09:00:00.000' AS DateTime), 1, N'In-Person', 1, N'Scheduled', 1, NULL)
INSERT [dbo].[Consultation] ([consultationID], [consultationDate], [servicePackageID], [consultationType], [customerID], [status], [consultantID], [notes]) VALUES (2, CAST(N'2025-01-16T10:00:00.000' AS DateTime), 2, N'Telehealth', 2, N'Scheduled', 2, NULL)
INSERT [dbo].[Consultation] ([consultationID], [consultationDate], [servicePackageID], [consultationType], [customerID], [status], [consultantID], [notes]) VALUES (3, CAST(N'2025-01-16T11:00:00.000' AS DateTime), 3, N'In-Person', 3, N'Scheduled', 3, NULL)
INSERT [dbo].[Consultation] ([consultationID], [consultationDate], [servicePackageID], [consultationType], [customerID], [status], [consultantID], [notes]) VALUES (4, CAST(N'2025-01-16T13:00:00.000' AS DateTime), 4, N'In-Person', 4, N'Scheduled', 4, NULL)
INSERT [dbo].[Consultation] ([consultationID], [consultationDate], [servicePackageID], [consultationType], [customerID], [status], [consultantID], [notes]) VALUES (5, CAST(N'2025-01-16T14:00:00.000' AS DateTime), 5, N'Telehealth', 5, N'Scheduled', 5, NULL)
SET IDENTITY_INSERT [dbo].[Consultation] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([customerID], [username], [password], [fullName], [email], [phone], [address], [accountStatus], [registrationDate], [dateOfBirth], [gender], [profilePicture]) VALUES (1, N'patient1', N'hash111', N'Alice Thompson', N'alice@email.com', N'1112223333', N'123 Patient St', N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), CAST(N'1990-01-15' AS Date), N'Female', NULL)
INSERT [dbo].[Customer] ([customerID], [username], [password], [fullName], [email], [phone], [address], [accountStatus], [registrationDate], [dateOfBirth], [gender], [profilePicture]) VALUES (2, N'patient2', N'hash222', N'Bob Martin', N'bob@email.com', N'2223334444', N'456 Patient Ave', N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), CAST(N'1985-06-20' AS Date), N'Male', NULL)
INSERT [dbo].[Customer] ([customerID], [username], [password], [fullName], [email], [phone], [address], [accountStatus], [registrationDate], [dateOfBirth], [gender], [profilePicture]) VALUES (3, N'patient3', N'hash333', N'Carol White', N'carol@email.com', N'3334445555', N'789 Patient Ln', N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), CAST(N'1995-03-25' AS Date), N'Female', NULL)
INSERT [dbo].[Customer] ([customerID], [username], [password], [fullName], [email], [phone], [address], [accountStatus], [registrationDate], [dateOfBirth], [gender], [profilePicture]) VALUES (4, N'patient4', N'hash444', N'David Brown', N'david@email.com', N'4445556666', N'321 Patient Rd', N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), CAST(N'1982-11-10' AS Date), N'Male', NULL)
INSERT [dbo].[Customer] ([customerID], [username], [password], [fullName], [email], [phone], [address], [accountStatus], [registrationDate], [dateOfBirth], [gender], [profilePicture]) VALUES (5, N'patient5', N'hash555', N'Eva Green', N'eva@email.com', N'5556667777', N'654 Patient Blvd', N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), CAST(N'1988-09-05' AS Date), N'Female', NULL)
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO

SET IDENTITY_INSERT [dbo].[Feedback] ON 

INSERT [dbo].[Feedback] ([feedbackID], [invoiceID], [rating], [comment], [date], [serviceType]) VALUES (1, 1, 5, N'Excellent service', CAST(N'2025-01-15T21:06:22.767' AS DateTime), N'General Checkup')
INSERT [dbo].[Feedback] ([feedbackID], [invoiceID], [rating], [comment], [date], [serviceType]) VALUES (2, 2, 4, N'Good experience', CAST(N'2025-01-15T21:06:22.767' AS DateTime), N'Specialist Consultation')
INSERT [dbo].[Feedback] ([feedbackID], [invoiceID], [rating], [comment], [date], [serviceType]) VALUES (3, 3, 5, N'Very professional', CAST(N'2025-01-15T21:06:22.767' AS DateTime), N'Pediatric Care')
INSERT [dbo].[Feedback] ([feedbackID], [invoiceID], [rating], [comment], [date], [serviceType]) VALUES (4, 4, 4, N'Helpful staff', CAST(N'2025-01-15T21:06:22.767' AS DateTime), N'General Checkup')
INSERT [dbo].[Feedback] ([feedbackID], [invoiceID], [rating], [comment], [date], [serviceType]) VALUES (5, 5, 5, N'Great care', CAST(N'2025-01-15T21:06:22.767' AS DateTime), N'Emergency Care')
SET IDENTITY_INSERT [dbo].[Feedback] OFF
GO
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([invoiceID], [customerID], [examinationID],[consultationID], [totalAmount], [paymentStatus], [paymentDate], [paymentMethod], [createdAt]) VALUES (1, 1, 1, null, CAST(100.00 AS Decimal(18, 2)), N'Paid', NULL, N'Credit Card', CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[Invoice] ([invoiceID], [customerID], [examinationID],[consultationID], [totalAmount], [paymentStatus], [paymentDate], [paymentMethod], [createdAt]) VALUES (2, 2, null, 2,CAST(250.00 AS Decimal(18, 2)), N'Paid', NULL, N'Insurance', CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[Invoice] ([invoiceID], [customerID], [examinationID], [consultationID],[totalAmount], [paymentStatus], [paymentDate], [paymentMethod], [createdAt]) VALUES (3, 3, null, 3,CAST(150.00 AS Decimal(18, 2)), N'Pending', NULL, N'Insurance', CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[Invoice] ([invoiceID], [customerID], [examinationID],[consultationID], [totalAmount], [paymentStatus], [paymentDate], [paymentMethod], [createdAt]) VALUES (4, 4, 4, null,CAST(120.00 AS Decimal(18, 2)), N'Paid', NULL, N'Cash', CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[Invoice] ([invoiceID], [customerID], [examinationID], [consultationID],[totalAmount], [paymentStatus], [paymentDate], [paymentMethod], [createdAt]) VALUES (5, 5, 5, null,CAST(180.00 AS Decimal(18, 2)), N'Paid', NULL, N'Credit Card', CAST(N'2025-01-15T21:06:22.767' AS DateTime))
SET IDENTITY_INSERT [dbo].[Invoice] OFF
GO
SET IDENTITY_INSERT [dbo].[MedicalExamination] ON 

INSERT [dbo].[MedicalExamination] ([examinationID], [examinationDate], [servicePackageID], [consultationType], [customerID], [status], [diagnosis], [treatmentPlan], [consultantID], [notes], [createdAt]) VALUES (1, CAST(N'2025-01-15T09:00:00.000' AS DateTime), 1, N'Regular', 1, N'Completed', N'Healthy', NULL, 1, NULL, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[MedicalExamination] ([examinationID], [examinationDate], [servicePackageID], [consultationType], [customerID], [status], [diagnosis], [treatmentPlan], [consultantID], [notes], [createdAt]) VALUES (2, CAST(N'2025-01-15T10:00:00.000' AS DateTime), 2, N'Emergency', 2, N'Completed', N'Minor infection', NULL, 2, NULL, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[MedicalExamination] ([examinationID], [examinationDate], [servicePackageID], [consultationType], [customerID], [status], [diagnosis], [treatmentPlan], [consultantID], [notes], [createdAt]) VALUES (3, CAST(N'2025-01-15T11:00:00.000' AS DateTime), 3, N'Regular', 3, N'Completed', N'Regular checkup', NULL, 3, NULL, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[MedicalExamination] ([examinationID], [examinationDate], [servicePackageID], [consultationType], [customerID], [status], [diagnosis], [treatmentPlan], [consultantID], [notes], [createdAt]) VALUES (4, CAST(N'2025-01-15T13:00:00.000' AS DateTime), 4, N'Regular', 4, N'Completed', N'Routine examination', NULL, 4, NULL, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[MedicalExamination] ([examinationID], [examinationDate], [servicePackageID], [consultationType], [customerID], [status], [diagnosis], [treatmentPlan], [consultantID], [notes], [createdAt]) VALUES (5, CAST(N'2025-01-15T14:00:00.000' AS DateTime), 5, N'Emergency', 5, N'Completed', N'Minor injury', NULL, 5, NULL, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
SET IDENTITY_INSERT [dbo].[MedicalExamination] OFF
GO
SET IDENTITY_INSERT [dbo].[MedicalRecord] ON 

INSERT [dbo].[MedicalRecord] ([recordID], [examinationID], [diagnosis], [treatmentPlan], [medicationsPrescribed], [createdAt], [notes]) VALUES (1, 1, N'Healthy', N'Regular exercise and balanced diet', N'None', CAST(N'2025-01-15T21:06:22.767' AS DateTime), NULL)
INSERT [dbo].[MedicalRecord] ([recordID], [examinationID], [diagnosis], [treatmentPlan], [medicationsPrescribed], [createdAt], [notes]) VALUES (2, 2, N'Minor infection', N'Antibiotics course', N'Amoxicillin', CAST(N'2025-01-15T21:06:22.767' AS DateTime), NULL)
INSERT [dbo].[MedicalRecord] ([recordID], [examinationID], [diagnosis], [treatmentPlan], [medicationsPrescribed], [createdAt], [notes]) VALUES (3, 3, N'Regular checkup', N'Continue current routine', N'Vitamins', CAST(N'2025-01-15T21:06:22.767' AS DateTime), NULL)
INSERT [dbo].[MedicalRecord] ([recordID], [examinationID], [diagnosis], [treatmentPlan], [medicationsPrescribed], [createdAt], [notes]) VALUES (4, 4, N'Routine examination', N'Regular follow-up', N'None', CAST(N'2025-01-15T21:06:22.767' AS DateTime), NULL)
INSERT [dbo].[MedicalRecord] ([recordID], [examinationID], [diagnosis], [treatmentPlan], [medicationsPrescribed], [createdAt], [notes]) VALUES (5, 5, N'Minor injury', N'Rest and ice application', N'Ibuprofen', CAST(N'2025-01-15T21:06:22.767' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[MedicalRecord] OFF
GO
SET IDENTITY_INSERT [dbo].[Permission] ON 

INSERT [dbo].[Permission] ([permissionID], [permissionName], [description]) VALUES (1, N'CREATE_PATIENT', N'Can create new patient records')
INSERT [dbo].[Permission] ([permissionID], [permissionName], [description]) VALUES (2, N'VIEW_MEDICAL_RECORDS', N'Can view patient medical records')
INSERT [dbo].[Permission] ([permissionID], [permissionName], [description]) VALUES (3, N'MANAGE_APPOINTMENTS', N'Can manage appointments')
INSERT [dbo].[Permission] ([permissionID], [permissionName], [description]) VALUES (4, N'PRESCRIBE_MEDICINE', N'Can prescribe medications')
INSERT [dbo].[Permission] ([permissionID], [permissionName], [description]) VALUES (5, N'MANAGE_CONTENT', N'Can manage website content')
SET IDENTITY_INSERT [dbo].[Permission] OFF
GO
SET IDENTITY_INSERT [dbo].[Posts] ON 

INSERT [dbo].[Posts] ([post_id], [title], [content], [created_by], [category_id], [status], [created_at], [updated_at]) VALUES (1, N'Heart Health Tips', N'Important tips for maintaining heart health...', 1, 2, 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[Posts] ([post_id], [title], [content], [created_by], [category_id], [status], [created_at], [updated_at]) VALUES (2, N'Child Vaccination Guide', N'Complete guide to child vaccinations...', 2, 3, 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[Posts] ([post_id], [title], [content], [created_by], [category_id], [status], [created_at], [updated_at]) VALUES (3, N'Healthy Diet Basics', N'Foundation of a healthy diet...', 3, 4, 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[Posts] ([post_id], [title], [content], [created_by], [category_id], [status], [created_at], [updated_at]) VALUES (4, N'Mental Wellness', N'Taking care of your mental health...', 4, 5, 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[Posts] ([post_id], [title], [content], [created_by], [category_id], [status], [created_at], [updated_at]) VALUES (5, N'Exercise Benefits', N'Why regular exercise is important...', 5, 1, 1, CAST(N'2025-01-15T21:06:22.763' AS DateTime), CAST(N'2025-01-15T21:06:22.763' AS DateTime))
SET IDENTITY_INSERT [dbo].[Posts] OFF
GO
SET IDENTITY_INSERT [dbo].[Professional] ON 

INSERT [dbo].[Professional] ([professionalID], [specialization], [gender], [dateOfBirth], [address], [officeHours], [qualification], [biography], [profilePicture], [status], [createdAt], [staffID]) VALUES (1, N'Cardiology', N'Male', CAST(N'1980-05-15T00:00:00.000' AS DateTime), N'123 Medical St', N'9:00-17:00', N'MD, FACC', N'Experienced cardiologist', NULL, N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 2)
INSERT [dbo].[Professional] ([professionalID], [specialization], [gender], [dateOfBirth], [address], [officeHours], [qualification], [biography], [profilePicture], [status], [createdAt], [staffID]) VALUES (2, N'Pediatrics', N'Female', CAST(N'1985-03-20T00:00:00.000' AS DateTime), N'456 Health Ave', N'8:00-16:00', N'MD, FAAP', N'Caring pediatrician', NULL, N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 3)
INSERT [dbo].[Professional] ([professionalID], [specialization], [gender], [dateOfBirth], [address], [officeHours], [qualification], [biography], [profilePicture], [status], [createdAt], [staffID]) VALUES (3, N'General Medicine', N'Male', CAST(N'1975-08-10T00:00:00.000' AS DateTime), N'789 Care Ln', N'10:00-18:00', N'MD', N'Family physician', NULL, N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 1)
INSERT [dbo].[Professional] ([professionalID], [specialization], [gender], [dateOfBirth], [address], [officeHours], [qualification], [biography], [profilePicture], [status], [createdAt], [staffID]) VALUES (4, N'Neurology', N'Female', CAST(N'1982-11-25T00:00:00.000' AS DateTime), N'321 Brain St', N'9:00-17:00', N'MD, PhD', N'Specialist neurologist', NULL, N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 4)
INSERT [dbo].[Professional] ([professionalID], [specialization], [gender], [dateOfBirth], [address], [officeHours], [qualification], [biography], [profilePicture], [status], [createdAt], [staffID]) VALUES (5, N'Dermatology', N'Male', CAST(N'1988-04-30T00:00:00.000' AS DateTime), N'654 Skin Ave', N'8:00-16:00', N'MD, FAAD', N'Expert dermatologist', NULL, N'Active', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 5)
SET IDENTITY_INSERT [dbo].[Professional] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([roleID], [roleName], [description]) VALUES (1, N'Admin', N'System administrator with full access')
INSERT [dbo].[Role] ([roleID], [roleName], [description]) VALUES (2, N'Doctor', N'Medical professional')
INSERT [dbo].[Role] ([roleID], [roleName], [description]) VALUES (3, N'Nurse', N'Nursing staff')
INSERT [dbo].[Role] ([roleID], [roleName], [description]) VALUES (4, N'Receptionist', N'Front desk staff')
INSERT [dbo].[Role] ([roleID], [roleName], [description]) VALUES (5, N'Content Manager', N'Manages website content')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (1, 1)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (1, 2)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (1, 3)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (1, 4)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (1, 5)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (2, 2)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (2, 3)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (2, 4)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (3, 2)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (3, 3)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (4, 1)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (4, 3)
INSERT [dbo].[RolePermissions] ([roleID], [permissionID]) VALUES (5, 5)
GO
SET IDENTITY_INSERT [dbo].[ServicePackage] ON 

INSERT [dbo].[ServicePackage] ([packageID], [packageName], [description],[type], [price], [duration], [createdAt]) VALUES (1, N'Basic Checkup', N'General health examination', N'basic', CAST(100.00 AS Decimal(18, 2)), 30, CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[ServicePackage] ([packageID], [packageName], [description], [type], [price], [duration], [createdAt]) VALUES (2, N'Comprehensive Exam', N'Full body checkup', N'basic', CAST(250.00 AS Decimal(18, 2)), 60, CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[ServicePackage] ([packageID], [packageName], [description], [type], [price], [duration], [createdAt]) VALUES (3, N'Specialist Consultation', N'Consultation with specialist', N'vip',CAST(150.00 AS Decimal(18, 2)), 45, CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[ServicePackage] ([packageID], [packageName], [description], [type], [price], [duration], [createdAt]) VALUES (4, N'Pediatric Package', N'Children''s health checkup', N'vip', CAST(120.00 AS Decimal(18, 2)), 40, CAST(N'2025-01-15T21:06:22.763' AS DateTime))
INSERT [dbo].[ServicePackage] ([packageID], [packageName], [description], [type], [price], [duration], [createdAt]) VALUES (5, N'Senior Care Package', N'Elderly health examination', N'vip', CAST(180.00 AS Decimal(18, 2)), 50, CAST(N'2025-01-15T21:06:22.763' AS DateTime))
SET IDENTITY_INSERT [dbo].[ServicePackage] OFF
GO
SET IDENTITY_INSERT [dbo].[Staff] ON 

INSERT [dbo].[Staff] ([staffID], [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES (1, N'John Smith', N'john.smith@medical.com', N'hash123', N'1234567890', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 1, N'Active', N'Administration', NULL)
INSERT [dbo].[Staff] ([staffID], [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES (2, N'Sarah Johnson', N'sarah.j@medical.com', N'hash456', N'2345678901', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 2, N'Active', N'Medical', NULL)
INSERT [dbo].[Staff] ([staffID], [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES (3, N'Mike Wilson', N'mike.w@medical.com', N'hash789', N'3456789012', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 3, N'Active', N'Medical', NULL)
INSERT [dbo].[Staff] ([staffID], [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES (4, N'Lisa Brown', N'lisa.b@medical.com', N'hash012', N'4567890123', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 4, N'Active', N'Reception', NULL)
INSERT [dbo].[Staff] ([staffID], [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES (5, N'Tom Davis', N'tom.d@medical.com', N'hash345', N'5678901234', CAST(N'2025-01-15T21:06:22.760' AS DateTime), 5, N'Active', N'Content', NULL)
SET IDENTITY_INSERT [dbo].[Staff] OFF
GO
SET IDENTITY_INSERT [dbo].[StaffReplies] ON 

INSERT [dbo].[StaffReplies] ([reply_id], [comment_id], [staff_id], [content], [status], [created_at]) VALUES (1, 1, 1, N'Thank you for your feedback!', 1, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[StaffReplies] ([reply_id], [comment_id], [staff_id], [content], [status], [created_at]) VALUES (2, 2, 2, N'Glad we could help!', 1, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[StaffReplies] ([reply_id], [comment_id], [staff_id], [content], [status], [created_at]) VALUES (3, 3, 3, N'Thanks for sharing your experience', 1, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[StaffReplies] ([reply_id], [comment_id], [staff_id], [content], [status], [created_at]) VALUES (4, 4, 4, N'We appreciate your comment', 1, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
INSERT [dbo].[StaffReplies] ([reply_id], [comment_id], [staff_id], [content], [status], [created_at]) VALUES (5, 5, 5, N'Thank you for your kind words', 1, CAST(N'2025-01-15T21:06:22.767' AS DateTime))
SET IDENTITY_INSERT [dbo].[StaffReplies] OFF
GO
SET IDENTITY_INSERT [dbo].[WorkingSchedule] ON 

INSERT [dbo].[WorkingSchedule] ([scheduleID], [professionalID], [workDay], [startTime], [endTime]) VALUES (1, 1, N'Monday', CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[WorkingSchedule] ([scheduleID], [professionalID], [workDay], [startTime], [endTime]) VALUES (2, 2, N'Tuesday', CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[WorkingSchedule] ([scheduleID], [professionalID], [workDay], [startTime], [endTime]) VALUES (3, 3, N'Wednesday', CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[WorkingSchedule] ([scheduleID], [professionalID], [workDay], [startTime], [endTime]) VALUES (4, 4, N'Thursday', CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[WorkingSchedule] ([scheduleID], [professionalID], [workDay], [startTime], [endTime]) VALUES (5, 5, N'Friday', CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time))
SET IDENTITY_INSERT [dbo].[WorkingSchedule] OFF
GO
ALTER TABLE [dbo].[Categories] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Comments] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Comments] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Customer] ADD  DEFAULT (getdate()) FOR [registrationDate]
GO
ALTER TABLE [dbo].[Feedback] ADD  DEFAULT (getdate()) FOR [date]
GO
ALTER TABLE [dbo].[Invoice] ADD  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[MedicalExamination] ADD  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[MedicalRecord] ADD  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[Posts] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Posts] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Posts] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Professional] ADD  CONSTRAINT [DF__Professio__statu__5812160E]  DEFAULT ('Active') FOR [status]
GO
ALTER TABLE [dbo].[Professional] ADD  CONSTRAINT [DF__Professio__creat__59063A47]  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[ServicePackage] ADD  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[Staff] ADD  CONSTRAINT [DF__Staff__hireDate__60A75C0F]  DEFAULT (getdate()) FOR [hireDate]
GO
ALTER TABLE [dbo].[StaffReplies] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[StaffReplies] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_ParentComment] FOREIGN KEY([parent_comment_id])
REFERENCES [dbo].[Comments] ([comment_id])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_ParentComment]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Post] FOREIGN KEY([post_id])
REFERENCES [dbo].[Posts] ([post_id])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_Post]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_User] FOREIGN KEY([customerID])
REFERENCES [dbo].[Customer] ([customerID])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_User]
GO
ALTER TABLE [dbo].[Consultation]  WITH CHECK ADD FOREIGN KEY([customerID])
REFERENCES [dbo].[Customer] ([customerID])
GO
ALTER TABLE [dbo].[Consultation]  WITH CHECK ADD FOREIGN KEY([servicePackageID])
REFERENCES [dbo].[ServicePackage] ([packageID])
GO
ALTER TABLE [dbo].[Consultation]  WITH CHECK ADD  CONSTRAINT [FK_Consultation_Professional] FOREIGN KEY([consultantID])
REFERENCES [dbo].[Professional] ([professionalID])
GO
ALTER TABLE [dbo].[Consultation] CHECK CONSTRAINT [FK_Consultation_Professional]
GO

ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY(invoiceID)
REFERENCES [dbo].[Invoice] ([invoiceID])
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD FOREIGN KEY([customerID])
REFERENCES [dbo].[Customer] ([customerID])
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD FOREIGN KEY([examinationID])
REFERENCES [dbo].[MedicalExamination] ([examinationID])
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD FOREIGN KEY([consultationID])
REFERENCES [dbo].[Consultation] ([consultationID])
GO
ALTER TABLE [dbo].[MedicalExamination]  WITH CHECK ADD FOREIGN KEY([customerID])
REFERENCES [dbo].[Customer] ([customerID])
GO
ALTER TABLE [dbo].[MedicalExamination]  WITH CHECK ADD FOREIGN KEY([servicePackageID])
REFERENCES [dbo].[ServicePackage] ([packageID])
GO
ALTER TABLE [dbo].[MedicalExamination]  WITH CHECK ADD  CONSTRAINT [FK_MedicalExamination_Professional] FOREIGN KEY([consultantID])
REFERENCES [dbo].[Professional] ([professionalID])
GO
ALTER TABLE [dbo].[MedicalExamination] CHECK CONSTRAINT [FK_MedicalExamination_Professional]
GO
ALTER TABLE [dbo].[MedicalRecord]  WITH CHECK ADD FOREIGN KEY([examinationID])
REFERENCES [dbo].[MedicalExamination] ([examinationID])
GO
ALTER TABLE [dbo].[Posts]  WITH CHECK ADD  CONSTRAINT [FK_Posts_Category] FOREIGN KEY([category_id])
REFERENCES [dbo].[Categories] ([category_id])
GO
ALTER TABLE [dbo].[Posts] CHECK CONSTRAINT [FK_Posts_Category]
GO
ALTER TABLE [dbo].[Posts]  WITH CHECK ADD  CONSTRAINT [FK_Posts_CreatedBy] FOREIGN KEY([created_by])
REFERENCES [dbo].[Staff] ([staffID])
GO
ALTER TABLE [dbo].[Posts] CHECK CONSTRAINT [FK_Posts_CreatedBy]
GO
ALTER TABLE [dbo].[Professional]  WITH CHECK ADD  CONSTRAINT [FK_Professional_Staff] FOREIGN KEY([staffID])
REFERENCES [dbo].[Staff] ([staffID])
GO
ALTER TABLE [dbo].[Professional] CHECK CONSTRAINT [FK_Professional_Staff]
GO
ALTER TABLE [dbo].[RolePermissions]  WITH CHECK ADD FOREIGN KEY([permissionID])
REFERENCES [dbo].[Permission] ([permissionID])
GO
ALTER TABLE [dbo].[RolePermissions]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Staff]  WITH CHECK ADD  CONSTRAINT [FK__Staff__roleID__619B8048] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Staff] CHECK CONSTRAINT [FK__Staff__roleID__619B8048]
GO
ALTER TABLE [dbo].[StaffReplies]  WITH CHECK ADD  CONSTRAINT [FK_StaffReplies_Comment] FOREIGN KEY([comment_id])
REFERENCES [dbo].[Comments] ([comment_id])
GO
ALTER TABLE [dbo].[StaffReplies] CHECK CONSTRAINT [FK_StaffReplies_Comment]
GO
ALTER TABLE [dbo].[StaffReplies]  WITH CHECK ADD  CONSTRAINT [FK_StaffReplies_Staff] FOREIGN KEY([staff_id])
REFERENCES [dbo].[Staff] ([staffID])
GO
ALTER TABLE [dbo].[StaffReplies] CHECK CONSTRAINT [FK_StaffReplies_Staff]
GO
ALTER TABLE [dbo].[WorkingSchedule]  WITH CHECK ADD  CONSTRAINT [FK__WorkingSc__profe__5BE2A6F2] FOREIGN KEY([professionalID])
REFERENCES [dbo].[Professional] ([professionalID])
GO
ALTER TABLE [dbo].[WorkingSchedule] CHECK CONSTRAINT [FK__WorkingSc__profe__5BE2A6F2]
GO
