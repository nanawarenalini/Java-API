package com.TimeTrackerApp.service;

import java.util.List;

import com.TimeTrackerApp.core.domain.Attendance;
import com.TimeTrackerApp.core.domain.AttendanceRequest;

public interface AttendanceService {

	List<Attendance> getAll();

	//void save(AttendanceRequest attendance, String emailId);

	void save(AttendanceRequest attendance);

	List<Attendance> getByQuantity(Integer index, String emailId);

	void update(AttendanceRequest attendanceReq, Integer id);

	void delete(Integer id);

}
