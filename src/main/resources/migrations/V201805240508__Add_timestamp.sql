ALTER TABLE postgres.codeapp.answers ADD  created_by  VARCHAR(255);
ALTER TABLE postgres.codeapp.answers ADD  modified_by VARCHAR(255);

ALTER TABLE postgres.codeapp.blood_stocks ADD created_by  VARCHAR(255);
ALTER TABLE postgres.codeapp.blood_stocks ADD modified_by  VARCHAR(255);

ALTER TABLE postgres.codeapp.invites ADD created_by  VARCHAR(255);
ALTER TABLE postgres.codeapp.invites ADD modified_by  VARCHAR(255);

ALTER TABLE postgres.codeapp.questions ADD created_by  VARCHAR(255);
ALTER TABLE postgres.codeapp.questions ADD modified_by  VARCHAR(255);

ALTER TABLE postgres.codeapp.user_donations ADD created_by  VARCHAR(255);
ALTER TABLE postgres.codeapp.user_donations ADD modified_by  VARCHAR(255);


