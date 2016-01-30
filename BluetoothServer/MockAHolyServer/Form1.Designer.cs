﻿namespace MockAHolyServer
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.listBoxDevices = new System.Windows.Forms.ListBox();
            this.bsDevices = new System.Windows.Forms.BindingSource(this.components);
            this.dataSetDevices = new MockAHolyServer.DataSet();
            this.btnFind = new System.Windows.Forms.Button();
            this.pb = new System.Windows.Forms.ProgressBar();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.btnInvite = new System.Windows.Forms.Button();
            this.btnConnection = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.bsDevices)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataSetDevices)).BeginInit();
            this.SuspendLayout();
            // 
            // listBoxDevices
            // 
            this.listBoxDevices.DataSource = this.bsDevices;
            this.listBoxDevices.DisplayMember = "DeviceName";
            this.listBoxDevices.FormattingEnabled = true;
            this.listBoxDevices.Location = new System.Drawing.Point(547, 12);
            this.listBoxDevices.Name = "listBoxDevices";
            this.listBoxDevices.Size = new System.Drawing.Size(155, 225);
            this.listBoxDevices.TabIndex = 0;
            this.listBoxDevices.ValueMember = "DeviceAddress";
            // 
            // bsDevices
            // 
            this.bsDevices.DataMember = "Devices";
            this.bsDevices.DataSource = this.dataSetDevices;
            // 
            // dataSetDevices
            // 
            this.dataSetDevices.DataSetName = "DataSet";
            this.dataSetDevices.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // btnFind
            // 
            this.btnFind.Location = new System.Drawing.Point(202, 12);
            this.btnFind.Name = "btnFind";
            this.btnFind.Size = new System.Drawing.Size(339, 23);
            this.btnFind.TabIndex = 1;
            this.btnFind.Text = "Find devices";
            this.btnFind.UseVisualStyleBackColor = true;
            this.btnFind.Click += new System.EventHandler(this.btnFind_Click);
            // 
            // pb
            // 
            this.pb.Location = new System.Drawing.Point(202, 214);
            this.pb.Name = "pb";
            this.pb.Size = new System.Drawing.Size(339, 23);
            this.pb.TabIndex = 2;
            this.pb.Visible = false;
            // 
            // listBox1
            // 
            this.listBox1.FormattingEnabled = true;
            this.listBox1.Location = new System.Drawing.Point(12, 12);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(184, 225);
            this.listBox1.TabIndex = 3;
            // 
            // btnInvite
            // 
            this.btnInvite.Location = new System.Drawing.Point(202, 41);
            this.btnInvite.Name = "btnInvite";
            this.btnInvite.Size = new System.Drawing.Size(339, 23);
            this.btnInvite.TabIndex = 4;
            this.btnInvite.Text = "Invite";
            this.btnInvite.UseVisualStyleBackColor = true;
            this.btnInvite.Click += new System.EventHandler(this.btnInvite_Click);
            // 
            // btnConnection
            // 
            this.btnConnection.Location = new System.Drawing.Point(202, 123);
            this.btnConnection.Name = "btnConnection";
            this.btnConnection.Size = new System.Drawing.Size(339, 23);
            this.btnConnection.TabIndex = 5;
            this.btnConnection.Text = "Start connection";
            this.btnConnection.UseVisualStyleBackColor = true;
            this.btnConnection.Click += new System.EventHandler(this.btnConnection_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(714, 261);
            this.Controls.Add(this.btnConnection);
            this.Controls.Add(this.btnInvite);
            this.Controls.Add(this.listBox1);
            this.Controls.Add(this.pb);
            this.Controls.Add(this.btnFind);
            this.Controls.Add(this.listBoxDevices);
            this.Name = "Form1";
            this.Text = "Form1";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.bsDevices)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataSetDevices)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxDevices;
        private System.Windows.Forms.BindingSource bsDevices;
        private DataSet dataSetDevices;
        private System.Windows.Forms.Button btnFind;
        private System.Windows.Forms.ProgressBar pb;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.Button btnInvite;
        private System.Windows.Forms.Button btnConnection;
    }
}
