package controllers.threads;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Dtos.userDtos.UserDto;
import utilities.Singleton;

import java.util.List;

public class TableRefresher implements Runnable {
    private final Thread tableRefreshThread;
    private final Singleton singleton;
    private ObservableList<UserDto> userDtoList;
    private TableView<UserDto> users_table;

    public TableRefresher() {
        singleton = Singleton.getInstance();
        tableRefreshThread = new Thread(this);
        tableRefreshThread.start();
    }

    public ObservableList<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(ObservableList<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public TableView<UserDto> getUsers_table() {
        return users_table;
    }

    public void setUsers_table(TableView<UserDto> users_table) {
        this.users_table = users_table;
    }


    @Override
    public void run() {
        int prevOnlinePlayers = 0;
        System.out.println("Refreshing table thread`");
        while (true) {
            if (singleton.getOnlineUsers().size() != prevOnlinePlayers) {
                List<UserDto> result = singleton.getOnlineUsers().stream().filter(userDto -> !(userDto.getUserName().equals(singleton.getCurrentUser()))).toList();
                userDtoList.clear();
                userDtoList.addAll(result);
                prevOnlinePlayers = singleton.getOnlineUsers().size();
            }
            users_table.refresh();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Thread getTableRefreshThread() {
        return tableRefreshThread;
    }
}
