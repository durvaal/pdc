package br.com.ufrn.projeto_2.dao;

import br.com.ufrn.projeto_2.domain.Checkpoint;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class CheckpointDao extends GenericDao<Checkpoint, Integer>{
	
	public CheckpointDao() {
       super(Checkpoint.class);
    }   

}
