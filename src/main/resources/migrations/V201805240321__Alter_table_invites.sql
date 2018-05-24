ALTER TABLE postgres.codeapp.invites
  ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE postgres.codeapp.invites
  ADD modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
