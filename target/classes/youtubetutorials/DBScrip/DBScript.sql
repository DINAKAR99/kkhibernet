CREATE OR REPLACE PROCEDURE sp_count_phones(IN personId INT, OUT phoneCount INT)
AS $$
BEGIN
    SELECT COUNT(*) INTO phoneCount 
    FROM Phone p  
    WHERE p.person_id = personId; 
END;
$$ LANGUAGE plpgsql;
