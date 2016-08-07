package hostelerp.com.dao;

import hostelerp.com.model.Block;
import hostelerp.com.model.CityState;
import hostelerp.com.model.College;
import hostelerp.com.model.Hostel;
import hostelerp.com.model.Payment;
import hostelerp.com.model.Room;
import hostelerp.com.model.RoomAllocation;
import hostelerp.com.model.Student;
import hostelerp.com.model.Users;
import hostelerp.com.util.ConstException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SuppressWarnings(
{ "unused", "unchecked" })
public class ProjectManagerDAOImpl implements ProjectManagerDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String ADD_USERS =
			"INSERT INTO users (username, password, firstname, usertype) VALUES (:username, :password, :firstname, :usertype)";
	final String GET_USERS =
			"SELECT *,  REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', userid) AS editBtn "
					+ "from users where status =:status ORDER BY created_on DESC LIMIT :startINdx, :endIndx ";
	final String GET_USERS_NUMENTRIES =
			"SELECT count(*) from users where status =:status";
	final String GET_USERS_SEARCH_PARAM =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', userid) AS editBtn "
					+ " from users where status =:status AND (username like :searchParam OR usertype like :searchParam) "
					+ "ORDER BY created_on DESC LIMIT :startINdx, :endIndx ";
	final String GET_USERS_SEARCH_PARAM_NUMENTRIES =
			"SELECT count(*) from users where status =:status AND (username like :searchParam OR usertype like :searchParam) ";
	final String DELETE_USER =
			"Update users set status=:status where userid =:userid";
	final String GET_USERS_VIA_ID = "SELECT * from users where userid =:userid";
	final String UPDATE_USERS_VIA_ID =
			"UPDATE users set username=:username, firstname=:firstname, usertype=:usertype "
					+ "Where userid=:userid";
	final String GET_CITY =
			"Select * from city where status =:status AND name like :name";
	final String GET_STATE =
			"Select * from state where status =:status AND name like :name";
	final String GET_STUDENTS =
			"SELECT c.name AS collegeName, s.*, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', s.id) AS editBtn "
					+ "from student s INNER JOIN college c ON c.id = s.collegeid "
					+ "where s.status =:status ORDER BY s.created_at DESC LIMIT :startIndx, :endIndx";
	final String GET_STUDENTS_NUMENTRIES =
			"SELECT count(*) from student where status =:status";
	final String GET_STUDENT_VIA_SEARCHPARAM =
			"SELECT c.name AS collegeName, s.*, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', s.id) AS editBtn "
					+ "from student s INNER JOIN college c ON c.id = s.collegeid "
					+ "where s.status =:status AND "
					+ "(s.name LIKE :searchParam OR s.rollno LIKE :searchParam OR s.course LIKE :searchParam OR s.messtype LIKE :searchParam) "
					+ "ORDER BY created_at LIMIT :startIndx, :endIndx";
	final String GET_STUDENT_NUMENTRIES_VIA_SEARCHPARAM =
			"SELECT count(*) from student where status =:status AND "
					+ "(name LIKE :searchParam OR rollno LIKE :searchParam OR course LIKE :searchParam OR messtype LIKE :searchParam) ";
	final String ADD_STUDENT =
			"INSERT INTO student (name, rollno, batch, course, messtype, address, state, city, country, mobileno, collegeid) "
					+ "VALUES (:name, :rollno, :batch, :course, :messtype, :address, :state, :city, :country, :mobileno, :collegeid)";
	final String GET_STUDENTS_VIA_ID =
			"Select c.name AS collegeName, s.* from student s "
					+ "INNER JOIN college c ON c.id = s.collegeid "
					+ "where s.id =:id AND s.status =:status";
	final String DELETE_STUDENTS_VIA_ID =
			"Update student set status =:status where id =:id";
	final String UPDATE_STUDENT_VIA_ID =
			"UPDATE student set name =:name, batch =:batch, course =:course, "
					+ "messtype =:messtype, address =:address, state =:state, "
					+ "city =:city, country =:country, mobileno =:mobileno, rollno =:rollno, collegeid =:collegeid WHERE id =:id";
	final String GET_HOSTELS =
			"SELECT c.name AS collegeName, h.*, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', h.id) AS editBtn from hostel h "
					+ "INNER JOIN college c ON c.id = h.collegeid "
					+ " where h.status =:status ORDER BY h.createdat DESC LIMIT :startIndx, :maxIndx";
	final String GET_HOSTEL_NUMENTRIES =
			"Select count(*) from hostel where status =:status";
	final String GET_HOSTELS_VIA_SEARCH_PARAMETER =
			"SELECT c.name AS collegeName, h.*, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', h.id) AS editBtn from hostel h "
					+ "INNER JOIN college c ON c.id = h.collegeid "
					+ "where h.status =:status AND (h.name LIKE :searchParam) "
					+ "ORDER BY h.createdat DESC LIMIT :startIndx, :maxIndx";
	final String GET_SEARCHPARAM_HOSTEL_NUMENTRIES =
			"Select count(*) from hostel where status =:status AND (name LIKE :searchParam)";
	final String GET_COLLEGES_API =
			"SELECT * from college where status =:status AND name LIKE :name";
	final String ADD_HOSTEL =
			"INSERT INTO hostel (name, collegeid, mobileno, address, state, city, country) "
					+ "values(:name, :collegeid, :mobileno, :address, :state, :city, :country)";
	final String GET_HOSTEL_VIA_ID =
			"Select c.name AS collegeName, h.* from hostel h "
					+ "INNER JOIN college c ON c.id = h.collegeid "
					+ "where h.status =:status AND h.id =:id";
	final String UPDATE_HOSTEL_VIA_ID = "Update hostel set name =:name, "
			+ "collegeid =:collegeid, mobileno =:mobileno, address =:address, "
			+ "state =:state, city =:city, country =:country where id =:id";
	final String DELETE_HOSTEL_VIA_ID =
			"Update hostel set status =:status where id =:id";
	final String GET_BLOCKS =
			"SELECT h.name AS hostelname, c.name AS collegename, b.*, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <!--<button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>-->', 'userid', b.id) AS editBtn from block b "
					+ " INNER JOIN hostel h ON h.id = b.hostelid "
					+ " INNER JOIN college c ON c.id = h.collegeid "
					+ "where b.status =:status "
					+ "ORDER BY b.createdat DESC LIMIT :startIndx, :endIndx";
	final String GET_BLOCKS_NUMENTRIES =
			"SELECT count(*) from block where status =:status ";
	final String GET_BLOCKS_VIA_SEARCHPARAM =
			"SELECT h.name AS hostelname, c.name AS collegename, b.*, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <!--<button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>-->', 'userid', b.id) AS editBtn from block b "
					+ " INNER JOIN hostel h ON h.id = b.hostelid "
					+ " INNER JOIN college c ON c.id = h.collegeid "
					+ "where b.status =:status  AND (b.blockname LIKE :searchParameter) "
					+ "ORDER BY b.createdat DESC LIMIT :startIndx, :endIndx";
	final String GET_BLOCKS_VIA_SEARCHPARAM_NUMENTRIES =
			"SELECT count(*) from block where status =:status  AND (blockname LIKE :searchParameter) ";
	final String GET_HOSTEL_NAME_API =
			"SELECT CONCAT(h.name, ' - ', c.name) AS name, h.id FROM hostel h "
					+ "INNER JOIN college c ON c.id = h.collegeid "
					+ "where c.status =:status AND h.status =:status AND (h.name LIKE :hostelname)";
	final String ADD_BLOCK =
			"INSERT INTO block (hostelid, blockname, nooffloor) values (:hostelid, :blockname, :nooffloor)";
	final String GET_BLOCKS_VIA_ID =
			"Select h.name AS hostelname, b.* from block b "
					+ " INNER JOIN hostel h ON h.id = b.hostelid "
					+ "where b.status =:status AND b.id =:id";
	final String UPDATE_BLOCKS_VIA_ID =
			"UPdate block set hostelid =:hostelid, blockname =:blockname, nooffloor =:nooffloor where id =:id";
	final String DELETE_BLOCK_VIA_ID =
			"Update block set status =:status where id =:id";
	final String GET_ALL_USERS = "SELECT * from users where status =:status";
	final String ADD_COLLEGE =
			"INSERT INTO college (name, address, state, city, country, mobileno) values (:name, :address, :state, :city, :country, :mobileno)";
	final String GET_COLLEGES =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <!--<button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>-->', 'userid', id) AS editBtn from college where status =:status LIMIT :startIndx, :endIndx";
	final String GET_COLLEGES_NUMENTRIES =
			"Select count(*) from college where status =:status";
	final String GET_COLLEGES_VIA_SEARCHPARAM =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <!--<button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>-->', 'userid', id) AS editBtn from college where status =:status AND (name LIKE :searchParam) LIMIT :startIndx, :endIndx";
	final String GET_COLLEGES_VIA_SEARCHPARAM_NUMENTRIES =
			"SELECT count(*) from college where status =:status AND (name LIKE :searchParam)";
	final String GET_COLLEGES_VIA_ID =
			"SELECT * from college where id =:id AND status =:status";
	final String UPDATE_COLLEGE_VIA_ID =
			"UPDATE college set name =:name, address =:address, state =:state, "
					+ "city =:city, country =:country, mobileno =:mobileno where id =:id";
	final String DELETE_COLLEGE_VIA_ID =
			"UPDATE college set status =:status where id =:id";
	final String CHK_COLLEGE_ID_REFERENCES =
			"SELECT temp.name FROM (SELECT c.name AS NAME FROM hostel h "
					+ "INNER JOIN college c "
					+ "ON "
					+ "c.id = h.collegeid where c.id =:id AND h.status =:status "
					+ "UNION "
					+ "SELECT c.name AS NAME FROM student s "
					+ "INNER JOIN college c "
					+ "ON "
					+ "c.id = s.collegeid where c.id =:id AND s.status =:status) AS temp";
	final String GET_ROOMS =
			"SELECT c.name AS collegename, h.name AS hostelname, b.blockname, r.floorname, r.noofperson, r.roomtype,  REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', r.id) AS editBtn FROM room r "
					+ "INNER JOIN hostel h "
					+ "ON h.id = r.hostelid "
					+ "INNER JOIN block b "
					+ "ON b.id = r.blockid "
					+ "INNER JOIN college c "
					+ "ON c.id = h.collegeid where r.status =:status ORDER BY r.created_at DESC LIMIT :startIndx, :maxIndx";
	final String GET_ROOMS_NUMENTRIES =
			"Select count(*) from room where status =:status";
	final String GET_ROOMS_VIA_SEARCHPARAMETER =
			"SELECT c.name AS collegename, h.name AS hostelname, b.blockname, r.floorname, r.noofperson, r.roomtype, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', r.id) AS editBtn FROM room r "
					+ "INNER JOIN hostel h "
					+ "ON h.id = r.hostelid "
					+ "INNER JOIN block b "
					+ "ON b.id = r.blockid "
					+ "INNER JOIN college c "
					+ "ON c.id = h.collegeid where r.status =:status AND "
					+ "(r.floorname LIKE :searchParam OR r.noofperson LIKE :searchParam) "
					+ "ORDER BY r.created_at DESC LIMIT :startIndx, :maxIndx";
	final String GET_ROOMS_NUMENTRIES_VIA_SEARCHPARAMETER =
			"Select count(*) from room r where r.status =:status AND (r.floorname LIKE :searchParam OR r.noofperson LIKE :searchParam)";
	final String GET_ALL_COLLEGES_VIA_STATUS =
			"Select * from college where status =:status";
	final String GET_HOSTEL_VIA_COLLEGE_ID =
			"SELECT h.id, h.name FROM hostel h "
					+ "INNER JOIN "
					+ "college c "
					+ "ON "
					+ "c.id = h.collegeid where c.id =:id AND h.status =:status";
	final String GET_BLOCKS_VIA_HOSTELID =
			"SELECT b.id, b.blockname, b.nooffloor FROM block b "
					+ "INNER JOIN hostel h ON " + "h.id = b.hostelid "
					+ "WHERE b.hostelid =:id AND b.status =:status ";
	final String ADD_ROOM =
			"INSERT INTO room (roomno, hostelid, blockid, roomtype, floorname, noofperson) "
					+ "VALUES (:roomno, :hostelid, :blockid, :roomtype, :floorname, :noofperson)";
	final String GET_ROOM_VIA_ID =
			"SELECT c.id AS collegeid, c.name AS collegename, h.name  AS hostelname, "
					+ "b.blockname, r.id, b.id AS blockid, r.roomtype, r.id, "
					+ "r.roomno, r.floorname, r.noofperson, r.hostelid FROM room r "
					+ "INNER JOIN  " + "block b  " + "ON b.id = r.blockid "
					+ "INNER JOIN  " + "hostel h " + "ON "
					+ "h.id = r.hostelid " + "INNER JOIN  " + "college c "
					+ "ON c.id = h.collegeid where r.id =:id ";
	final String GET_HOSTELS_VIA_ROOM_ID =
			"SELECT h.id, h.name FROM "
					+ "(SELECT c.id FROM college c "
					+ "INNER JOIN "
					+ "hostel h "
					+ "ON c.id = h.collegeid "
					+ "INNER JOIN room r "
					+ "ON r.hostelid = h.id "
					+ "WHERE r.id =:id) AS a, hostel h WHERE a.id = h.collegeid AND h.status =:status";
	final String GET_BLOCKS_VIA_ROOM_ID =
			"SELECT b.id, b.blockname, b.nooffloor FROM room r "
					+ "INNER JOIN  " + "hostel h " + "ON  "
					+ "r.hostelid = h.id " + "INNER JOIN  " + "block b "
					+ "ON  " + "b.hostelid = r.hostelid "
					+ "WHERE r.id =:id AND b.status =:status";

	final String UPDATE_ROOM_VIA_ID =
			"UPDATE room set roomno =:roomno, hostelid =:hostelid, blockid =:blockid, "
					+ "roomtype =:roomtype, floorname =:floorname, noofperson =:noofperson  "
					+ "WHERE id =:id";
	final String GET_BLOCKS_VIA_HOSTEL_BLOCK_ID =
			"SELECT * from block where hostelid =:hostelid AND id =:id AND status =:status";
	final String GET_ROOM_ALLOCATION_DETAILS =
			"SELECT ra.status AS roomallocationstatus, ra.roomid, s.name AS studentname, s.collegeid, s.rollno, c.name AS collegename, s.rollno, h.name As hostelname, ra.entry_date, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', ra.id) AS editBtn "
					+ "FROM (SELECT rt.roomid, rt.id, rt.created_at, rt.entry_date, rt.status, rt.studentid "
					+ "FROM room_allocation rt "
					+ "INNER JOIN (SELECT MAX(id) AS id FROM room_allocation "
					+ "GROUP BY studentid) AS d "
					+ "ON d.id = rt.id ) AS ra "
					+ "INNER JOIN "
					+ "room r "
					+ "ON "
					+ "r.id = ra.roomid "
					+ "INNER JOIN hostel h ON h.id = r.hostelid "
					+ "INNER JOIN student s "
					+ "ON s.id = ra.studentid "
					+ "INNER JOIN college c "
					+ "ON c.id = s.collegeid WHERE ra.status !=:status LIMIT :startIndx, :maxIndx";
	final String GET_ROOM_ALLOCATION_DETAILS_NUMENTRIES =
			"SELECT COUNT(*) FROM "
					+ "(SELECT rt.roomid, rt.id, rt.created_at, rt.entry_date, rt.status, rt.studentid  FROM room_allocation rt "
					+ "INNER JOIN (SELECT MAX(id) AS id FROM room_allocation "
					+ "GROUP BY studentid) AS d " + "ON d.id = rt.id ) AS ra "
					+ "INNER JOIN " + "room r " + "ON " + "r.id = ra.roomid "
					+ "INNER JOIN hostel h ON h.id = r.hostelid "
					+ "INNER JOIN student s " + "ON s.id = ra.studentid "
					+ "INNER JOIN college c "
					+ "ON c.id = s.collegeid WHERE ra.status !=:status ";
	final String GET_ROOM_ALLOCATION_DETAILS_VIA_SEARCHPARAM =
			"SELECT ra.status AS roomallocationstatus, ra.roomid, s.name AS studentname, s.collegeid, c.name AS collegename, s.rollno, h.name As hostelname, ra.entry_date, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', ra.id) AS editBtn  "
					+ "FROM (SELECT rt.roomid, rt.id, rt.created_at, rt.entry_date, rt.status, rt.studentid "
					+ "FROM room_allocation rt "
					+ "INNER JOIN (SELECT MAX(id) AS id FROM room_allocation "
					+ "GROUP BY studentid) AS d "
					+ "ON d.id = rt.id ) AS ra "
					+ "INNER JOIN "
					+ "room r "
					+ "ON "
					+ "r.id = ra.roomid "
					+ "INNER JOIN hostel h ON h.id = r.hostelid "
					+ "INNER JOIN student s "
					+ "ON s.id = ra.studentid "
					+ "INNER JOIN college c "
					+ "ON c.id = s.collegeid WHERE ra.status !=:status AND (h.name LIKE :searchParam OR c.name LIKE :searchParam OR s.rollno LIKE :searchParam) LIMIT :startIndx, :maxIndx";
	final String GET_ROOM_ALLOCATION_DETAILS_VIA_SEARCHPARAM_NUMENTRIES =
			"SELECT COUNT(*) "
					+ "FROM (SELECT rt.roomid, rt.id, rt.created_at, rt.entry_date, rt.status, rt.studentid  "
					+ "FROM room_allocation rt  "
					+ "INNER JOIN (SELECT MAX(id) AS id FROM room_allocation  "
					+ "GROUP BY studentid) AS d  "
					+ "ON d.id = rt.id ) AS ra  "
					+ "INNER JOIN  "
					+ "room r  "
					+ "ON  "
					+ "r.id = ra.roomid  "
					+ "INNER JOIN hostel h ON h.id = r.hostelid  "
					+ "INNER JOIN student s  "
					+ "ON s.id = ra.studentid  "
					+ "INNER JOIN college c  "
					+ "ON c.id = s.collegeid WHERE ra.status !=:status AND (h.name LIKE :searchParam OR c.name LIKE :searchParam OR s.rollno LIKE :searchParam)";
	final String GET_STUDENT_VIA_COLLEGE_ID =
			"SELECT CONCAT(s.rollno, ' - ', s.NAME) AS NAME, s.id  FROM student s  LEFT OUTER JOIN  room_allocation ra "
					+ "ON ra.studentid = s.id "
					+ "WHERE s.collegeid =:collegeId AND s.status =:status AND (ra.status <> 'alloted' OR ra.id IS NULL)";
	final String GET_COLLGES_VIA_COLLGE_ID =
			"SELECT * from hostel where collegeid =:collegeId AND status =:status";
	final String GET_ROOMS_VIA_HOSTEL_ID =
			"SELECT r.roomno, r.id AS roomid, IFNULL(ra.rowcount,r.noofperson) AS seatsavailable "
					+ "FROM room r "
					+ "LEFT OUTER JOIN "
					+ "(SELECT h.roomid,(r.noofperson - COUNT(*)) AS rowcount "
					+ "FROM "
					+ "room_allocation h "
					+ "INNER JOIN room r ON h.roomid = r.id "
					+ "WHERE h.status = 'alloted' "
					+ "GROUP BY h.roomid) ra ON r.id = ra.roomid "
					+ "WHERE r.status =:status AND r.hostelid =:hostelId AND (ra.rowcount > 0 OR ra.roomid IS NULL)";
	final String CHK_ROOM_ALLOTED =
			"Select count(*) from room_allocation where studentid =:studentid AND status =:status";
	final String ADD_ROOM_ALLOCATION =
			"INSERT INTO room_allocation (roomid, studentid, entry_date) VALUES (:roomid, :studentid, :entry_date)";
	final String GET_ROOM_ALLOCATION_VIA_ROOM_ALLOCATIONID =
			"SELECT ra.roomid, ra.id AS roomallocationid, r.roomno, CONCAT(s.rollno, ' - ', s.name) AS studentname, "
					+ "s.id AS studentid, h.id AS hostelid, c.id AS collegeid, ra.status AS roomallocationstatus "
					+ "FROM room_allocation ra "
					+ "INNER JOIN room r ON r.id = ra.roomid "
					+ "INNER JOIN student s ON s.id = ra.studentid "
					+ "INNER JOIN  hostel h ON h.id = r.hostelid "
					+ "INNER JOIN college c ON h.collegeid = c.id "
					+ "WHERE ra.id =:roomId " + "GROUP BY h.name, c.name";
	final String UPDATE_ROOM_ALLOCATION_VIA_ID =
			"Update room_allocation set roomid =:roomid, studentid =:studentid, last_modified =:last_modified, status =:status where id =:id";
	final String GET_ROOMS_VIA_HOSTEL_ID_AND_ROOMNO =
			"SELECT count(*) "
					+ "FROM room r "
					+ "LEFT OUTER JOIN "
					+ "(SELECT h.roomid,(r.noofperson - COUNT(*)) AS rowcount "
					+ "FROM "
					+ "room_allocation h "
					+ "INNER JOIN room r ON h.roomid = r.id "
					+ "WHERE h.status = 'alloted' "
					+ "GROUP BY h.roomid) ra ON r.id = ra.roomid "
					+ "WHERE  r.hostelid =:hostelId AND  r.roomno =:roomno  AND (ra.rowcount > 0 OR ra.roomid IS NULL)";
	final String ADD_PAYMENT =
			"INSERT INTO payment (studentid, collegeid, status, amount) values (:studentid, :collegeid, :status, :amount)";
	final String GET_PAYMENTS =
			"SELECT s.name AS studentname, p.status, c.name AS collegename, p.amount, REPLACE('<button pk_id=\"userid\" title=\"Edit\""
					+ " class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', p.id) AS editBtn  "
					+ "FROM payment p  "
					+ "INNER JOIN college c  "
					+ "ON c.id = p.collegeid "
					+ "INNER JOIN student s  "
					+ "ON s.id = p.studentid "
					+ "ORDER BY p.created_at DESC  "
					+ "LIMIT :startIndx, :endIndx";
	final String GET_PAYMENTS_NUMENTRIES = "select count(*) from payment";
	final String GET_PAYMENTS_VIA_SEARCHPARAM =
			"SELECT s.name AS studentname, c.name AS collegename, p.amount, p.status, "
					+ "REPLACE('<button pk_id=\"userid\" title=\"Edit\""
					+ " class=\"btn btn-success btn-xs btn-perspective hfmsEditBtn\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelBtn\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', p.id) AS editBtn  "
					+ "FROM payment p  " + "INNER JOIN college c  "
					+ "ON c.id = p.collegeid " + "INNER JOIN student s  "
					+ "ON s.id = p.studentid " + "WHERE "
					+ "s.name LIKE :searchParam OR c.name LIKE :searchParam "
					+ "ORDER BY p.created_at DESC  "
					+ "LIMIT :startIndx, :endIndx";
	final String GET_PAYMENTS_NUMENTRIES_VIA_SEARCHPARAM =
			"SELECT count(*) FROM payment p  " + "INNER JOIN college c  "
					+ "ON c.id = p.collegeid " + "INNER JOIN student s  "
					+ "ON s.id = p.studentid " + "WHERE "
					+ "s.name LIKE :searchParam OR c.name LIKE :searchParam";
	final String GET_PAYMENTS_VIA_ID =
			"SELECT s.name AS studentname, c.name AS collegename, p.* FROM payment p "
					+ "INNER JOIN student s " + "ON s.id = p.studentid "
					+ "INNER JOIN college c " + "ON "
					+ "c.id = p.collegeid where p.id =:id";
	final String UPDATE_PAYMENTS_VIA_ID =
			"Update payment set amount =:amount where id =:id";

	@Override
	public void addUsers(Users user) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("username", user.getUsername());
		paramMap.put("password", user.getPassword());
		paramMap.put("firstname", user.getFirstname());
		paramMap.put("usertype", user.getUsertype());

		namedParameterJdbcTemplate.update(ADD_USERS, paramMap);
	}

	@Override
	public
			List<Users> getUsers(int startIndx, int maxIndx,
					String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startINdx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);

		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_USERS, paramMap,
				new BeanPropertyRowMapper<Users>(Users.class));
	}

	@Override
	public int getUsersNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.queryForInt(GET_USERS_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Users> getUsersViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParam) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startINdx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParam + "%");

		return namedParameterJdbcTemplate.query(GET_USERS_SEARCH_PARAM,
				paramMap, new BeanPropertyRowMapper(Users.class));

	}

	@Override
	public int getUsersNumEntriesViaSearchParam(String status,
			String searchParam) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("searchParam", searchParam + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_USERS_SEARCH_PARAM_NUMENTRIES, paramMap);
	}

	@Override
	public void deleteUsers(int userId, String sTATUS_INACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", userId);
		paramMap.put("status", sTATUS_INACTIVE);

		namedParameterJdbcTemplate.update(DELETE_USER, paramMap);

	}

	@Override
	public List<Users> getUsersViaId(int userId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", userId);

		return namedParameterJdbcTemplate.query(GET_USERS_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Users.class));
	}

	@Override
	public void updateUsersViaId(Users users) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("username", users.getUsername());
		paramMap.put("firstname", users.getFirstname());
		paramMap.put("usertype", users.getUsertype());
		paramMap.put("userid", users.getUserid());

		namedParameterJdbcTemplate.update(UPDATE_USERS_VIA_ID, paramMap);

	}

	@Override
	public List<CityState> getCityAndState(String locationname, String status,
			boolean isCity) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("name", locationname + "%");
		if (isCity)
		{
			return namedParameterJdbcTemplate.query(GET_CITY, paramMap,
					new BeanPropertyRowMapper(CityState.class));
		} else
		{
			return namedParameterJdbcTemplate.query(GET_STATE, paramMap,
					new BeanPropertyRowMapper(CityState.class));
		}

	}

	@Override
	public List<Student> getStudentViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.query(GET_STUDENT_VIA_SEARCHPARAM,
				paramMap, new BeanPropertyRowMapper(Student.class));
	}

	@Override
	public List<Student> getStudent(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);

		return namedParameterJdbcTemplate.query(GET_STUDENTS, paramMap,
				new BeanPropertyRowMapper(Student.class));
	}

	@Override
	public int getStudentNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.queryForInt(GET_STUDENTS_NUMENTRIES,
				paramMap);
	}

	@Override
	public int getStudentNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_STUDENT_NUMENTRIES_VIA_SEARCHPARAM, paramMap);
	}

	@Override
	public void addStudent(Student student) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", student.getName());
		paramMap.put("rollno", student.getRollno());
		paramMap.put("batch", student.getBatch());
		paramMap.put("course", student.getCourse());
		paramMap.put("messtype", student.getMesstype());
		paramMap.put("address", student.getAddress());
		paramMap.put("state", student.getState());
		paramMap.put("city", student.getCity());
		paramMap.put("country", student.getCountry());
		paramMap.put("mobileno", student.getMobileno());
		paramMap.put("collegeid", student.getCollegeid());

		namedParameterJdbcTemplate.update(ADD_STUDENT, paramMap);

	}

	@Override
	public List<Student> getStudentsViaId(int studentId, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", studentId);
		paramMap.put("status", status);
		return namedParameterJdbcTemplate.query(GET_STUDENTS_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Student.class));
	}

	@Override
	public void deleteStudentViaId(int studentId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", "deactive");
		paramMap.put("id", studentId);

		namedParameterJdbcTemplate.update(DELETE_STUDENTS_VIA_ID, paramMap);
	}

	@Override
	public void updateStudensViaId(Student student) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", student.getName());
		paramMap.put("batch", student.getBatch());
		paramMap.put("course", student.getCourse());
		paramMap.put("messtype", student.getMesstype());
		paramMap.put("address", student.getAddress());
		paramMap.put("state", student.getState());
		paramMap.put("city", student.getCity());
		paramMap.put("country", student.getCountry());
		paramMap.put("mobileno", student.getMobileno());
		paramMap.put("id", student.getId());
		paramMap.put("rollno", student.getRollno());
		paramMap.put("collegeid", student.getCollegeid());

		namedParameterJdbcTemplate.update(UPDATE_STUDENT_VIA_ID, paramMap);
	}

	@Override
	public List<Hostel> getHostels(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.query(GET_HOSTELS, paramMap,
				new BeanPropertyRowMapper<Hostel>(Hostel.class));
	}

	@Override
	public List<Hostel> getHostelsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.query(
				GET_HOSTELS_VIA_SEARCH_PARAMETER, paramMap,
				new BeanPropertyRowMapper<Hostel>(Hostel.class));
	}

	@Override
	public List<Hostel> getCollegeNameApi(String locationname, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", locationname + "%");
		paramMap.put("status", status);

		return namedParameterJdbcTemplate.query(GET_COLLEGES_API, paramMap,
				new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public void addHostel(Hostel hostel) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", hostel.getName());
		paramMap.put("collegeid", hostel.getCollegeid());
		paramMap.put("mobileno", hostel.getMobileno());
		paramMap.put("address", hostel.getAddress());
		paramMap.put("state", hostel.getState());
		paramMap.put("city", hostel.getCity());
		paramMap.put("country", hostel.getCountry());

		namedParameterJdbcTemplate.update(ADD_HOSTEL, paramMap);
	}

	@Override
	public List<Hostel> getHostelViaId(int hostelId, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("id", hostelId);

		return namedParameterJdbcTemplate.query(GET_HOSTEL_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public void updateHostelViaId(Hostel hostel) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", hostel.getName());
		paramMap.put("collegeid", hostel.getCollegeid());
		paramMap.put("mobileno", hostel.getMobileno());
		paramMap.put("address", hostel.getAddress());
		paramMap.put("state", hostel.getState());
		paramMap.put("city", hostel.getCity());
		paramMap.put("country", hostel.getCountry());
		paramMap.put("id", hostel.getId());

		namedParameterJdbcTemplate.update(UPDATE_HOSTEL_VIA_ID, paramMap);

	}

	@Override
	public int getHostelsNumEntries(String status) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);

		return namedParameterJdbcTemplate.queryForInt(GET_HOSTEL_NUMENTRIES,
				paramMap);

	}

	@Override
	public int getHostelsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.queryForInt(
				GET_SEARCHPARAM_HOSTEL_NUMENTRIES, paramMap);

	}

	@Override
	public void deleteHostelViaId(int hostelId, String sTATUS_DEACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_DEACTIVE);
		paramMap.put("id", hostelId);

		namedParameterJdbcTemplate.update(DELETE_HOSTEL_VIA_ID, paramMap);
	}

	@Override
	public List<Block> getBlocks(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);

		return namedParameterJdbcTemplate.query(GET_BLOCKS, paramMap,
				new BeanPropertyRowMapper(Block.class));
	}

	@Override
	public int getBlocksNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.queryForInt(GET_BLOCKS_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Block> getBlocksViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("searchParameter", searchParameter + "%");

		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_SEARCHPARAM,
				paramMap, new BeanPropertyRowMapper(Block.class));

	}

	@Override
	public int getBlocksViaSearchParamNumEntriesViaSearchParam(
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParameter", searchParameter + "%");

		return namedParameterJdbcTemplate.queryForInt(
				GET_BLOCKS_VIA_SEARCHPARAM_NUMENTRIES, paramMap);
	}

	@Override
	public List<Hostel> getHostelNameApi(String locationname, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("hostelname", locationname + "%");

		return namedParameterJdbcTemplate.query(GET_HOSTEL_NAME_API, paramMap,
				new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public void addBlock(Block block) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("hostelid", block.getHostelid());
		paramMap.put("blockname", block.getBlockname());
		paramMap.put("nooffloor", block.getNooffloor());

		namedParameterJdbcTemplate.update(ADD_BLOCK, paramMap);
	}

	@Override
	public List<Block> getBlockViaId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("id", id);
		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Block.class));

	}

	@Override
	public void updateBlockViaId(Block block) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("hostelid", block.getHostelid());
		paramMap.put("blockname", block.getBlockname());
		paramMap.put("nooffloor", block.getNooffloor());
		paramMap.put("id", block.getId());

		namedParameterJdbcTemplate.update(UPDATE_BLOCKS_VIA_ID, paramMap);
	}

	@Override
	public void deleteBlockViaId(int id, String status) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("id", id);

		namedParameterJdbcTemplate.update(DELETE_BLOCK_VIA_ID, paramMap);

	}

	@Override
	public List<Users> getUsers(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_ALL_USERS, paramMap,
				new BeanPropertyRowMapper(Users.class));
	}

	@Override
	public void addColleges(College college) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", college.getName());
		paramMap.put("address", college.getAddress());
		paramMap.put("state", college.getState());
		paramMap.put("city", college.getCity());
		paramMap.put("country", college.getcountry());
		paramMap.put("mobileno", college.getMobileno());

		namedParameterJdbcTemplate.update(ADD_COLLEGE, paramMap);
	}

	@Override
	public List<College> getColleges(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);

		return namedParameterJdbcTemplate.query(GET_COLLEGES, paramMap,
				new BeanPropertyRowMapper<College>(College.class));

	}

	@Override
	public int getCollegesNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.queryForInt(GET_COLLEGES_NUMENTRIES,
				paramMap);

	}

	@Override
	public List<College> getCollegesViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.query(GET_COLLEGES_VIA_SEARCHPARAM,
				paramMap, new BeanPropertyRowMapper<College>(College.class));
	}

	@Override
	public int getCollegeNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.queryForInt(
				GET_COLLEGES_VIA_SEARCHPARAM_NUMENTRIES, paramMap);
	}

	@Override
	public List<College> getCollegeViaId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("id", id);

		return namedParameterJdbcTemplate.query(GET_COLLEGES_VIA_ID, paramMap,
				new BeanPropertyRowMapper(College.class));
	}

	@Override
	public void updateCollegesViaId(College college) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", college.getName());
		paramMap.put("address", college.getAddress());
		paramMap.put("state", college.getState());
		paramMap.put("city", college.getCity());
		paramMap.put("country", college.getcountry());
		paramMap.put("mobileno", college.getMobileno());
		paramMap.put("id", college.getId());

		namedParameterJdbcTemplate.update(UPDATE_COLLEGE_VIA_ID, paramMap);
	}

	@Override
	public void deleteCollegeViaId(int id, String dEACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		paramMap.put("status", "active");
		List<College> list =
				namedParameterJdbcTemplate.query(CHK_COLLEGE_ID_REFERENCES,
						paramMap, new BeanPropertyRowMapper(College.class));
		if (list.size() == 0)
		{
			paramMap.put("status", dEACTIVE);
			namedParameterJdbcTemplate.update(DELETE_COLLEGE_VIA_ID, paramMap);
		} else
		{
			throw new ConstException(ConstException.ERR_CODE_REFERENCES_KEY,
					ConstException.ERR_MSG_REFERENCES_KEY);
		}

	}

	@Override
	public
			List<Room>
			getRooms(int startIndx, int maxIndx, String sTATUS_ACTIVE)
					throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		return namedParameterJdbcTemplate.query(GET_ROOMS, paramMap,
				new BeanPropertyRowMapper<Room>(Room.class));

	}

	@Override
	public int getRoomsNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.queryForInt(GET_ROOMS_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Room> getRoomsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.query(GET_ROOMS_VIA_SEARCHPARAMETER,
				paramMap, new BeanPropertyRowMapper<Room>(Room.class));
	}

	@Override
	public int getRoomsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_ROOMS_NUMENTRIES_VIA_SEARCHPARAMETER, paramMap);
	}

	@Override
	public List<College> getAllCollegesViaStatus(String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_ALL_COLLEGES_VIA_STATUS,
				paramMap, new BeanPropertyRowMapper<College>(College.class));
	}

	@Override
	public List<Hostel> getHostelsViaCollegeId(int collegeId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", collegeId);
		paramMap.put("status", "active");
		return namedParameterJdbcTemplate.query(GET_HOSTEL_VIA_COLLEGE_ID,
				paramMap, new BeanPropertyRowMapper<Hostel>(Hostel.class));
	}

	@Override
	public List<Block> getBlockViaHostelId(int collegeId, int hostelId)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", hostelId);
		paramMap.put("status", "active");

		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_HOSTELID,
				paramMap, new BeanPropertyRowMapper<Block>(Block.class));
	}

	@Override
	public void addRoom(Room room) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("roomno", room.getRoomno());
		paramMap.put("hostelid", room.getHostelid());
		paramMap.put("blockid", room.getBlockid());
		paramMap.put("roomtype", room.getRoomtype());
		paramMap.put("floorname", room.getFloorname());
		paramMap.put("noofperson", room.getNoofperson());

		namedParameterJdbcTemplate.update(ADD_ROOM, paramMap);
	}

	@Override
	public List<Room> getRoomViaId(int id) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		return namedParameterJdbcTemplate.query(GET_ROOM_VIA_ID, paramMap,
				new BeanPropertyRowMapper<Room>(Room.class));
	}

	@Override
	public List<Hostel> getHostelsViaRoomId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_HOSTELS_VIA_ROOM_ID,
				paramMap, new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public List<Block> getBlocksViaRoomId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_ROOM_ID,
				paramMap, new BeanPropertyRowMapper<Block>(Block.class));
	}

	@Override
	public void updateRoomViaId(Room room) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", room.getId());
		paramMap.put("roomno", room.getRoomno());
		paramMap.put("hostelid", room.getHostelid());
		paramMap.put("blockid", room.getBlockid());
		paramMap.put("roomtype", room.getRoomtype());
		paramMap.put("floorname", room.getFloorname());
		paramMap.put("noofperson", room.getNoofperson());

		namedParameterJdbcTemplate.update(UPDATE_ROOM_VIA_ID, paramMap);
	}

	@Override
	public List<Block> getNoFloorsViaHostelAndBlockId(int hostelId,
			int blockId, String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", blockId);
		paramMap.put("hostelid", hostelId);
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_HOSTEL_BLOCK_ID,
				paramMap, new BeanPropertyRowMapper<Block>(Block.class));
	}

	@Override
	public List<RoomAllocation> getRoomAllocationDetails(int startIndx,
			int maxIndx, String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		return namedParameterJdbcTemplate.query(GET_ROOM_ALLOCATION_DETAILS,
				paramMap, new BeanPropertyRowMapper<RoomAllocation>(
						RoomAllocation.class));

	}

	@Override
	public int getRoomAllocationDetailsNumEntries(String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.queryForInt(
				GET_ROOM_ALLOCATION_DETAILS_NUMENTRIES, paramMap);
	}

	@Override
	public List<RoomAllocation> getRoomAllocationDetailsViaSearchParam(
			int startIndx, int maxIndx, String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate
				.query(GET_ROOM_ALLOCATION_DETAILS_VIA_SEARCHPARAM, paramMap,
						new BeanPropertyRowMapper<RoomAllocation>(
								RoomAllocation.class));
	}

	@Override
	public int getRoomAllocationDetailsNumEntriesViaSearchParam(
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_ROOM_ALLOCATION_DETAILS_VIA_SEARCHPARAM_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Student> getStudentViaCollegeId(int collegeId,
			String sTATUS_ACTIVE) throws Exception
	{

		Map paramMap = new HashMap();
		paramMap.put("collegeId", collegeId);
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.query(GET_STUDENT_VIA_COLLEGE_ID,
				paramMap, new BeanPropertyRowMapper<Student>(Student.class));

	}

	@Override
	public List<Hostel> getHostelViaCollegeId(int collegeId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("collegeId", collegeId);
		paramMap.put("status", "active");

		return namedParameterJdbcTemplate.query(GET_COLLGES_VIA_COLLGE_ID,
				paramMap, new BeanPropertyRowMapper<Hostel>(Hostel.class));
	}

	@Override
	public List<Room> getRoomNoViaHostelId(int hostelId, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("hostelId", hostelId);
		paramMap.put("status", "active");

		return namedParameterJdbcTemplate.query(GET_ROOMS_VIA_HOSTEL_ID,
				paramMap, new BeanPropertyRowMapper<Room>(Room.class));
	}

	@Override
	public int chkIsAlloted(RoomAllocation roomAllocation) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("studentid", roomAllocation.getStudentid());
		paramMap.put("status", "alloted");

		return namedParameterJdbcTemplate.queryForInt(CHK_ROOM_ALLOTED,
				paramMap);
	}

	@Override
	public void addRoomAllocation(RoomAllocation roomAllocation)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("roomid", roomAllocation.getRoomid());
		paramMap.put("studentid", roomAllocation.getStudentid());
		paramMap.put("entry_date", roomAllocation.getEntry_date());

		namedParameterJdbcTemplate.update(ADD_ROOM_ALLOCATION, paramMap);

	}

	@Override
	public List<RoomAllocation> getRoomAllocationDetailsViaRoomId(
			int roomAllocationId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("roomId", roomAllocationId);
		return namedParameterJdbcTemplate
				.query(GET_ROOM_ALLOCATION_VIA_ROOM_ALLOCATIONID, paramMap,
						new BeanPropertyRowMapper<RoomAllocation>(
								RoomAllocation.class));
	}

	@Override
	public void updateRoomAllocationViaId(RoomAllocation roomAllocation)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("roomid", roomAllocation.getRoomid());
		paramMap.put("studentid", roomAllocation.getStudentid());
		paramMap.put("last_modified", roomAllocation.getLast_modified());
		paramMap.put("status", roomAllocation.getStatus());
		paramMap.put("id", roomAllocation.getId());

		namedParameterJdbcTemplate.update(UPDATE_ROOM_ALLOCATION_VIA_ID,
				paramMap);

	}

	public int getRoomNoViaHostelId_RoomNo(int hostelId, int RoomNo)
	{
		Map paramMap = new HashMap();
		paramMap.put("hostelId", hostelId);
		paramMap.put("roomno", RoomNo);

		return namedParameterJdbcTemplate.queryForInt(
				GET_ROOMS_VIA_HOSTEL_ID_AND_ROOMNO, paramMap);

	}

	@Override
	public void addPayment(Payment payment) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("studentid", payment.getStudentid());
		paramMap.put("collegeid", payment.getCollegeid());
		paramMap.put("status", payment.getStatus());
		paramMap.put("amount", payment.getAmount());

		namedParameterJdbcTemplate.update(ADD_PAYMENT, paramMap);
	}

	@Override
	public List<Payment> getPayments(int startIndx, int endIndx)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", endIndx);

		return namedParameterJdbcTemplate.query(GET_PAYMENTS, paramMap,
				new BeanPropertyRowMapper<Payment>(Payment.class));
	}

	@Override
	public int getPaymentsNumEntries(String searchParam) throws Exception
	{
		Map paramMap = new HashMap();
		return namedParameterJdbcTemplate.queryForInt(GET_PAYMENTS_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Payment> getPaymentsViaSearchParam(int startIndx, int maxIndx,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("searchParam", searchParameter + "%");
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		return namedParameterJdbcTemplate.query(GET_PAYMENTS_VIA_SEARCHPARAM,
				paramMap, new BeanPropertyRowMapper<Payment>(Payment.class));
	}

	@Override
	public int getPaymentsNumEntriesNumEntriesViaSearchParam(
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_PAYMENTS_NUMENTRIES_VIA_SEARCHPARAM, paramMap);
	}

	@Override
	public List<Payment> getPaymentsViaId(int id) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		return namedParameterJdbcTemplate.query(GET_PAYMENTS_VIA_ID, paramMap,
				new BeanPropertyRowMapper<Payment>(Payment.class));

	}

	@Override
	public void updatePaymentsViaId(Payment payment) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", payment.getId());
		paramMap.put("amount", payment.getAmount());

		namedParameterJdbcTemplate.update(UPDATE_PAYMENTS_VIA_ID, paramMap);

	}

}
