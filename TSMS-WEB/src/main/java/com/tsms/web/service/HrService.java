package com.tsms.web.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.pojo.AwardNomination;
import com.tsms.web.dao.HrDao;
import com.tsms.web.dao.SendMessageDao;
import com.tsms.web.dao.UserRoleMasterDao;
import com.tsms.web.dto.HrSendMSGDto;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;

@Service
public class HrService {
	
	@Autowired
	private HrDao hrdao;

	@Autowired
	private UserRoleMasterDao userRoleMasterDao;
	
	@Autowired
	private SendMessageDao sendMessageDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<UserMaster> getAllEmployeeList() {
		
		List<UserMaster> allemList=hrdao.findAll();
		return allemList;
	}

	public Long saveMessageTodb(HrSendMSGDto hrSendMSGDto) {
		
			try {
				if(!hrSendMSGDto.getMsgFile().isEmpty() || hrSendMSGDto.getMsgFile()!=null)
				insertPdfOrExcel(hrSendMSGDto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		HrSendMSG hrSendMSG= this.modelMapper.map(hrSendMSGDto, HrSendMSG.class);
		return sendMessageDao.save(hrSendMSG);
	}

	public List<HrSendMSG> getLast7daysMessageFromDb(UserMaster reciver, boolean isManager,boolean isEmployee) {
		List<HrSendMSG> messageList=sendMessageDao.getLast7daysMessageFromDb( reciver,  isManager,isEmployee);
		
		for(HrSendMSG msglist:messageList)
		{
			UserMaster msgsenderuser=hrdao.findByEmpCode(msglist.getMsgSender());	
			msglist.setMsgSender(msgsenderuser.getFirstName()+" "+msgsenderuser.getLastName());
			
			if(msglist.getMsgReciver().startsWith("DRA"))
			{
				UserMaster msgreciveruser=hrdao.findByEmpCode(msglist.getMsgReciver());	
				msglist.setMsgReciver(msgreciveruser.getFirstName()+" "+msgreciveruser.getLastName());
			}
		}
		
		
		return messageList;
	}
	
	public List<HrSendMSG> getLast7daysMessageSentByHrFromDb(UserMaster loggedInUser) {
		List<HrSendMSG> messageList=sendMessageDao.getLast7daysMessageFromDb(loggedInUser.getEmpCode());
		return messageList;
	}

	public List<AwardNomination> getAwardNominationsList(String month,String year) {
		return sendMessageDao.getAwardNominationsList(month,year);
	}
	
	
	
	public HrSendMSGDto insertPdfOrExcel(HrSendMSGDto hrSendMSGDto) throws IOException {
        String filePath = null;
        Long insert = null;
        String addedOn = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        try
        {
        if (!hrSendMSGDto.getMsgFile().isEmpty()) {
            String contentType = hrSendMSGDto.getMsgFile().getContentType();
            byte[] bytes = hrSendMSGDto.getMsgFile().getBytes();
            
            if (contentType.startsWith("image/jpg") || contentType.startsWith("image/png") || contentType.startsWith("image/jpeg")
                    || contentType.startsWith("application/pdf") || contentType.startsWith("application/vnd.ms-excel")
                    || contentType.startsWith("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    || contentType.startsWith("application/msword")
                    || contentType.startsWith("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {


                filePath = "/opt/DRA/MSGFiles/".concat(addedOn.concat("/"));
                Path path = Paths.get(filePath);

                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }

                Path filePaths = Paths.get(filePath + hrSendMSGDto.getMsgFile().getOriginalFilename());
                Files.write(filePaths, bytes);
                hrSendMSGDto.setMsgFilePath("/opt/DRA/MSGFiles/" + addedOn.concat("/") + hrSendMSGDto.getMsgFile().getOriginalFilename());
            }
        }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return hrSendMSGDto;
    }
	
	

}
