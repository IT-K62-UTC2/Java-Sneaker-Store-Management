CREATE VIEW staff_view AS
SELECT staff.id, staff.username, staff.fullname, staff.address,
       staff.phone_number, staff.email, staff.gender, staff.avatar, staff.created_at, staff.`status`,
       staff.cccd, position.name AS `position.name`, position.id AS `position.id`
FROM staff LEFT JOIN position ON staff.id_position = position.id ORDER BY staff.created_at DESC;