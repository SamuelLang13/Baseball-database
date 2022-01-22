package cz.langsamu.tjv.baseballdatabase.client_console.ui.console;

import cz.langsamu.tjv.baseballdatabase.client_console.data.AwardClient;
import cz.langsamu.tjv.baseballdatabase.client_console.model.AwardDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.AwardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AwardConsole {

    private final AwardClient client;

    @Autowired
    public AwardConsole(AwardClient client) {
        this.client = client;
    }

    @ShellMethod("Register new award")
    public void registerAward(String name) {
        try {
            var award = new AwardDTO(null, name);
            var newAward = client.create(award);
            AwardView.printAward(newAward);
        } catch (RuntimeException e) {
            AwardView.printError(e);
        }
    }

    @ShellMethod("Get award by his ID")
    public void getAwardById(Long id) {
        try {
            var award = client.readById(id);
            AwardView.printAward(award);
        } catch (RuntimeException e) {
            AwardView.printError(e);
        }
    }

    @ShellMethod("Get all awards")
    public void getAllAwards() {
        try {
            var awards = client.readAll();
            AwardView.printAllAwards(awards);
        } catch (RuntimeException e) {
            AwardView.printError(e);
        }
    }

    @ShellMethod("Update award")
    public void updateAward(Long id, String name) {
        try {
            var award = new AwardDTO(null, name);
            var newAward = client.update(id, award);
            AwardView.printAward(newAward);
        } catch (RuntimeException e) {
            AwardView.printError(e);
        }
    }

    @ShellMethod("Remove award")
    public void removeAward(Long id) {
        try {
            client.delete(id);
        } catch (RuntimeException e) {
            AwardView.printError(e);
        }
    }
}
