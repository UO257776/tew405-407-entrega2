package com.tew.persistence;

import java.util.List;

import com.tew.model.PisoParaVisitar;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface PisoParaVisitarDao {

	List<PisoParaVisitar> getPisosParaVisitar();
	void save(PisoParaVisitar a) throws AlreadyPersistedException;
	void update(PisoParaVisitar a) throws NotPersistedException;
	PisoParaVisitar findById(Long idPiso, Long idCliente);
	void delete(Long idPiso, Long idCliente) throws NotPersistedException;

}
