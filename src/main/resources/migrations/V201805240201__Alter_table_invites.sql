ALTER TABLE postgres.codeapp.invites ADD answer_text TEXT;
ALTER TABLE postgres.codeapp.invites ADD donation_event_id VARCHAR(255) REFERENCES donation_events(id);
