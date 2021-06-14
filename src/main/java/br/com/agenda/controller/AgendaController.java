package br.com.agenda.controller;

import br.com.agenda.model.Agenda;
import br.com.agenda.repository.AgendaRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendaController {

    @Autowired
    private AgendaRepository AgendaRepository;

    @GetMapping("/gerenciarAgenda")
    public String listarAgenda(Model model) {
        model.addAttribute("listaAgenda", AgendaRepository.findAll());
        return "gerenciar_agenda";
    }

    @GetMapping("/novaAgenda")
    public String novoAgenda(Model model) {
        model.addAttribute("agenda", new Agenda());
        return "editar_agenda";
    }

    @GetMapping("/editarAgenda/{id}")
    public String editarAgenda(@PathVariable("id") long idPaciente, Model model) {
        Optional<Agenda> agenda = AgendaRepository.findById(idPaciente);
        model.addAttribute("agenda", agenda.get());
        return "editar_agenda";
    }

    @PostMapping("/salvarAgenda")
    public String salvarAgenda(Agenda agenda, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_agenda";
        }
        AgendaRepository.save(agenda);
        return "redirect:/gerenciarAgenda";
    }

    @GetMapping("/excluirAgenda/{id}")
    public String excluirAgenda(@PathVariable("id") long idAgenda) {
        AgendaRepository.deleteById(idAgenda);
        return "redirect:/gerenciarAgenda";
    }
    
    
}