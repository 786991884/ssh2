package xubh.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xubh.model.User;
import xubh.service.UserService;
import xubh.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 6379002911899175164L;
	private UserService userService;

	private List<User> userList;
	private int id;
	private User user;

	// 分页
	private int pageNum = 1;

	public String viewUserByPage() {
		PageBean pageBean = userService.getPageBean(pageNum);
		ActionContext.getContext().getValueStack().push(pageBean);
		// ActionContext.getContext().put("pageBean", pageBean);
		return "viewUserByPage";
	}

	public String viewUser() {
		User user = userService.findAllById(id);
		setUser(user);
		return "view";
	}

	public String doEditUser() {
		userService.update(user);
		return SUCCESS;
	}

	public String deleteUser() {
		userService.deleteUserById(id);
		return SUCCESS;
	}

	public String addUser() {
		userService.save(user);
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<User> getUserList() {
		userList = userService.findAllUsers();
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
